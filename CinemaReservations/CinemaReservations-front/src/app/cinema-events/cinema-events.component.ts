import {AfterViewInit, Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import { FullCalendarComponent } from '@fullcalendar/angular';
import { EventInput } from '@fullcalendar/core';
import timeGrigPlugin from '@fullcalendar/timegrid';
import {ActivatedRoute} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {EventPropertiesModalComponent} from './components/event-properties-modal/event-properties-modal.component';
import * as moment from 'moment';
import {ApiService} from '../services/api/api.service';

@Component({
  selector: 'app-cinema-events',
  templateUrl: './cinema-events.component.html',
  styleUrls: ['./cinema-events.component.scss']
})
export class CinemaEventsComponent implements OnInit, AfterViewInit {
  constructor(private route: ActivatedRoute, private renderer2: Renderer2, public dialog: MatDialog, private apiService: ApiService) { }
  @ViewChild('calendar') calendarComponent: FullCalendarComponent; // the #calendar in the template
  @ViewChild('events') events: ElementRef;
  calendarPlugins = [dayGridPlugin, timeGrigPlugin, interactionPlugin];
  calendarEvents: EventInput[]= [];

  public shows;
  public films;
  public halls;
  public activeHall

  handleDateClick(arg) {
    // this.openDialog()
    // if (confirm('Would you like to add an event to ' + arg.dateStr + ' ?')) {
    //   this.calendarEvents = this.calendarEvents.concat({ // add new event data. must create new array
    //     title: 'New Event',
    //     start: arg.date,
    //     allDay: arg.allDay
    //   });
    // }
  }

  handleEvent($event) {
  }

  handleEventClick($event) {
    const event = {
      filmTitle: $event.el.innerText,
      id: $event.event.id,
      startDate: $event.event.start,
      duration: (moment($event.event.end).valueOf() - moment($event.event.start).valueOf()) / 60000,
      originalEvent: $event
    }
    this.openDialog(event, 'view')
  }

  handleDrop($event) {
    const newEvent = {
      filmTitle: $event.draggedEl.innerText,
      fimId: $event.draggedEl.id,
      startDate: $event.date,
      duration: (this.calendarComponent.getApi().defaultTimedEventDuration.milliseconds as any) / 60000,
      originalEvent: $event
    }
    this.openDialog(newEvent, 'create')
  }

  ngOnInit(): void {
    this.shows = this.route.snapshot.data['shows'];
    this.films = this.route.snapshot.data['films'];
    this.halls = this.route.snapshot.data['halls'];
    this.activeHall = this.halls[0].id;
  }

  ngAfterViewInit() {
    this.films.map((film) => {
      const showEvent = this.renderer2.createElement('div');
      this.renderer2.addClass(showEvent, 'fc-event')
      this.renderer2.setAttribute(showEvent, 'id', film.id)
      const title = this.renderer2.createText(film.title);
      this.renderer2.appendChild(showEvent, title);
      this.renderer2.appendChild(this.events.nativeElement, showEvent);
    });

    new Draggable(this.events.nativeElement, {
      itemSelector: '.fc-event',
      eventData(eventEl) {
        return {
          title: eventEl.innerText,
          create: false
      };
      }
    });

    this.populateEvents();
    this.calendarComponent.getApi().updateSize()
  }

  openDialog(event: any, mode: any): void {
    const dialogRef = this.dialog.open(EventPropertiesModalComponent, {
      data: {
        event: event,
        mode: mode
      }
    });

    dialogRef.afterClosed().subscribe(modalData => {
     if(modalData) {
       if (modalData.mode === 'create') {
         const result = modalData.event;
         result.originalEvent.start = result.startDate;
         result.originalEvent.end = moment(result.startDate).add(result.duration, 'm').toDate();
         this.calendarEvents = this.calendarEvents.concat({
           title: result.filmTitle,
           start: result.startDate,
           end: moment(result.startDate).add(result.duration, 'm').toDate()
         });

         const newShow = {
           date: moment(result.startDate).valueOf().toString(),
           time: result.duration.toString(),
           film: this.films.filter(film => film.id == result.fimId)[0],
           theatreHall: {
             id: this.activeHall
           }
         }
         this.apiService.postShows(newShow).subscribe(resp => {
           this.populateEvents()
         });
       }
       if(modalData.mode === 'view') {
         this.apiService.deleteShow(modalData.event.id).subscribe(resp => {
           this.populateEvents()
         });
       }
     }

    });
  }

  populateEvents() {
    this.apiService.getShows().subscribe(resp1 => {
      const events = [];
      const shows = resp1 as Array<any>;

      if(shows) {
        shows
          .filter(el => { return el.theatreHall.id == this.activeHall})
          .forEach(el => {
            const title = this.films.filter(film => {
              return el.film.id == film.id;
            })[0].title
            const newEvent = {
              title: title,
              start: moment(moment(el.date, "x").format("DD MMM YYYY hh:mm a")).toDate(),
              end: moment(moment(el.date, "x").format("DD MMM YYYY hh:mm a")).add(el.time, 'm').toDate(),
              id: el.id
            }
            events.push(newEvent);
          })
      }
      this.calendarEvents = events;
    });
  }

  setActiveHall($event){
    this.activeHall = $event.tab.textLabel;
    this.populateEvents()
    this.calendarComponent.getApi().updateSize()
  }

}
