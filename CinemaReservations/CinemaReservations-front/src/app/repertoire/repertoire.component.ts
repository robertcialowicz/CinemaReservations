import { AfterViewInit, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {ApiService} from '../services/api/api.service';
import * as moment from 'moment';

@Component({
  selector: 'app-repertoire',
  templateUrl: './repertoire.component.html',
  styleUrls: ['./repertoire.component.scss']
})
export class RepertoireComponent implements OnInit, AfterViewInit {
  repertoire: any;
  shows: any;
  films: any;

  constructor(
    private route: ActivatedRoute, public dialog: MatDialog,
    private router: Router,
    private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.shows = this.route.snapshot.data['shows'];
    this.films = this.route.snapshot.data['films'];
    if(this.shows) {
      this.shows.forEach(show => {
        show.date = (moment(show.date, "x").format("LLLL"))
        this.films.map(film => {
          if (film.id == show.film.id) {
            film.movieShows.push(show)
          }
        })
      })
    }
    this.films = this.films.filter(film => {
      return film.movieShows.length > 0
    })

    // this.repertoire = this.route.snapshot.data['repertoire'];
    //   this.apiService.getFilms().subscribe((resp: Array<any>) => {
    //   this.apiService.getShows().subscribe((resp1: Array<any>) => {
    //     if(resp1) {
    //       resp1.forEach(show => {
    //         resp.map(film => {
    //           if (film.id == show.film.id) {
    //             film.movieShows.push(show)
    //           }
    //         })
    //       })
    //
    //       this.repertoire =  resp.filter(film => {
    //         return film.movieShows.length > 0
    //       })
    //       console.log(this.repertoire)
    //     }
    //   })
    // })
  }

  ngAfterViewInit(): void {
  }

  makeReservation(eventId: any) {
    this.router.navigate(['/cinema-reservations/make-reservation'], {queryParams: { eventId}});
  }
}

