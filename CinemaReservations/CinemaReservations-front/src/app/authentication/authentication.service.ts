import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {User} from './fake-backend.interceptor';
import {HttpClient} from '@angular/common/http';
import {CookieService} from 'ngx-cookie-service';
import {ApiService} from '../services/api/api.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient, private cookieService: CookieService, private apiService: ApiService) {
    this.currentUserSubject = new BehaviorSubject<any>(localStorage.getItem('currentUser'))
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
    const user = { username: username, password: password };
    localStorage.setItem('currentUser', user.username + ':' + user.password);
    this.currentUserSubject.next(user);
    return of(user);
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.cookieService.set('Authorization', null);
    this.currentUserSubject.next(null);
  }
}
