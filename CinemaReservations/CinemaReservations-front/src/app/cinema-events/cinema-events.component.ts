import {AfterViewInit, Component, ElementRef, OnInit, Renderer2, ViewChild} from '@angular/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin, {Draggable} from '@fullcalendar/interaction';
import { FullCalendarComponent } from '@fullcalendar/angular';
import { EventInput } from '@fullcalendar/core';
import timeGrigPlugin from '@fullcalendar/timegrid';
import {ActivatedRoute} from '@angular/router';


@Component({
  selector: 'app-cinema-events',
  templateUrl: './cinema-events.component.html',
  styleUrls: ['./cinema-events.component.scss']
})
export class CinemaEventsComponent implements OnInit, AfterViewInit {
  constructor(private route: ActivatedRoute, private renderer2: Renderer2) { }

  @ViewChild('calendar') calendarComponent: FullCalendarComponent; // the #calendar in the template
  @ViewChild('events') events: ElementRef;
  calendarPlugins = [dayGridPlugin, timeGrigPlugin, interactionPlugin];
  calendarEvents: EventInput[] = [
    { title: 'Event Now', start: new Date() }
  ];

  public activeShows;

  gotoPast() {
    let calendarApi = this.calendarComponent.getApi();
    calendarApi.gotoDate('2000-01-01'); // call a method on the Calendar object
  }

  handleDateClick(arg) {
    if (confirm('Would you like to add an event to ' + arg.dateStr + ' ?')) {
      this.calendarEvents = this.calendarEvents.concat({ // add new event data. must create new array
        title: 'New Event',
        start: arg.date,
        allDay: arg.allDay
      });
    }
  }

  handleDrop($event) {
    console.log('drop event')
    console.log($event)
  }

  ngOnInit(): void {
    this.activeShows = this.route.snapshot.data['activeShows'];
  }

  ngAfterViewInit() {
    this.activeShows.map((show) => {
      console.log(this)
      const showEvent = this.renderer2.createElement('div');
      this.renderer2.addClass(showEvent, 'fc-event')
      this.renderer2.setAttribute(showEvent, 'id', show.generalData.id)
      this.renderer2.setAttribute(showEvent, 'duration', show.screeningData.screeningTime)
      const name = this.renderer2.createText(show.generalData.name);
      this.renderer2.appendChild(showEvent, name);
      this.renderer2.appendChild(this.events.nativeElement, showEvent);
    });

    console.log(this.events)
    new Draggable(this.events.nativeElement, {
      itemSelector: '.fc-event',
      eventData(eventEl) {
        return {
          title: eventEl.innerText
        };
      }
    });
  }

}
