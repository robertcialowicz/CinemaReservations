import { Injectable } from '@angular/core';
import {ApiService} from '../api/api.service';
import {ActivatedRouteSnapshot, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {filter, first, map} from 'rxjs/operators';

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
export class ActiveShowsResolversService {

  constructor(private apiService: ApiService) { }

  resolve(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<any>|Promise<any>|any {
    return this.apiService.getShows().pipe(first(),
      map((response) => {
       return response.filter((show) => {
         return show.screeningData.active === true;
        });
       })
    );
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
    return this.apiService.getRepertoire().pipe(first(),
      map((response) => {
        return response.map((show) => {
          this.apiService.getImage(show.film.posterId).subscribe(data => {
            const reader = new FileReader();
            reader.addEventListener('load', () => {
              // show.film.poster = reader.result;
              // this is for presentation purpose only
              show.film.poster = `../../../assets/posters/${show.film.posterId}.jpg`
            }, false);

            if (data) {
              reader.readAsDataURL(data);
            }
          }, error => {
            console.log(error);
          });
          console.log(show);
          return show;
        });
      })
    );
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
