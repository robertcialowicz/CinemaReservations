import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';
import { delay } from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

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

  getShowsCount() {
    return this.httpClient.get('api/shows/count')
  }

  postReservation(reservation: any) {
    return this.httpClient.post('api/reservations', reservation);
  }

  getReservationsCount() {
    return this.httpClient.get('api/reservations/count');
  }

  getFilms() {
    return this.httpClient.get('api/films')
  }

  postFilms(film: any) {
    return this.httpClient.post('api/films', film);
  }

  getFilmsCount() {
    return this.httpClient.get('api/films/count')
  }

  getHalls() {
    return this.httpClient.get('api/halls')
  }

  getReservations() {
    return this.httpClient.get('api/reservations')
  }
}

