import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import { delay } from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  repertoire = [
    {
      film: {
        id: 1,
        name: 'Once upon a time in Hollywood',
        type: ['crime', 'drama'],
        director: 'Quentin Tarantino',
        scenario: 'Quentin Tarantino',
        releaseDate: '21 maja 2019',
        description: 'Aktor Rick Dalton i jego przyjaciel kaskader powracają do Hollywood. Mężczyźni próbują odnaleźć się w przemyśle filmowym, który ewoluował podczas ich nieobecności.',
        cast: ['Brad Pit', 'Leonardo Dicaprio', 'Margot Robie'],
        ageLimit: null,
        posterId: 0,
        poster: null
      },
      shows: [
        {
          id: 0,
          date: '2020-05-07 20:30'
        },
        {
          id: 1,
          date: '2020-05-07 20:30'
        }
      ]
    },
    {
      film: {
        id: 2,
        name: 'The Blues Brothers',
        type: ['comedy', 'musical'],
        director: 'Johna Landisa',
        scenario: 'Johna Landisa',
        releaseDate: '20 czerwca 1980',
        description: 'Po wyjściu z więzienia Jake Blues organizuje powrót swojego starego zespołu muzycznego, by zebrać pieniądze na uratowanie sierocińca, w którym wychował się ze swoim bratem, Elwoodem.',
        cast: ['John Belushi', 'Dan Aykroyd', 'Carrie Fisher'],
        ageLimit: null,
        posterId: 1,
        poster: null
      },
      shows: [
        {
          id: 2,
          date: '2020-05-10 18:30'
        },
        {
          id: 3,
          date: '2020-05-17 20:00'
        }
      ]
    }
  ];

  event = {
    id: 0,
    name: 'Once upon a time in Hollywood',
    date: '2020-05-07 20:30'
  };

  reservations = [
    {
      id: 0,
      film: 'The Blues Brothers',
      date: '2020-05-10 18:30',
      hall: 2,
      email: "peron@op.pl",
      seats: [
        "G6",
        "G7"
      ]
    },
    {
      id: 1,
      film: 'Once upon a time in Hollywood',
      date: '2020-05-07 20:30',
      hall: 4,
      email: "aaaa@op.pl",
      seats: [
        "H11",
        "B6"
      ]
    }
  ]

  constructor(private httpClient: HttpClient) { }

  getShows() {
    return this.httpClient.get('api/shows')
  }

  getShow(showid: any) {
    return this.httpClient.get(`api/shows/${showid}`);
  }

  postShows(show: any) {
    return this.httpClient.post('api/shows', show);
  }

  deleteShow(showid: any) {
    return this.httpClient.delete(`api/shows/${showid}`);
  }

  postReservation(reservation: any) {
    return this.httpClient.post('api/reservations', reservation);
  }

  getFilms() {
    return this.httpClient.get('api/films')
  }

  postFilms(film: any) {
    return this.httpClient.post('api/films', film);
  }

  getHalls() {
    return this.httpClient.get('api/halls')
  }

  getEvent(id: any) {
    return of(this.event).pipe(delay(1000));
  }

  getReservations() {
    return of(this.reservations).pipe(delay(1000));
  }
}

