import { Injectable } from '@angular/core';
import {ApiService} from '../api/api.service';
import {ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {Observable, of} from 'rxjs';
import {filter, first, map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FilmsResolversService {

  constructor(private apiService: ApiService) { }

    resolve(
      route: ActivatedRouteSnapshot,
      state: RouterStateSnapshot
    ): Observable<any>|Promise<any>|any {
      return this.apiService.getFilms();
    }
}

@Injectable({
  providedIn: 'root'
})
export class ShowsResolversService {

  constructor(private apiService: ApiService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any>|Promise<any>|any {
    return this.apiService.getShows();
  }
}

@Injectable({
  providedIn: 'root'
})
export class RepertoireResolversService {

  constructor(private apiService: ApiService) {
  }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any> | Promise<any> | any {
    return;
  }
}

@Injectable({
  providedIn: 'root'
})
export class SelectedEventResolverService {

  constructor(private apiService: ApiService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any>|Promise<any>|any {
      this.apiService.getEvent(route.queryParams['eventId']).subscribe(res => {
      return res;
    });
  }
}

@Injectable({
  providedIn: 'root'
})
export class ReservationsResolverService {

  constructor(private apiService: ApiService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any>|Promise<any>|any {
      return this.apiService.getReservations();
  }
}

@Injectable({
  providedIn: 'root'
})
export class HallsResolverService {

  constructor(private apiService: ApiService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any>|Promise<any>|any {
    return this.apiService.getHalls();
  }
}


