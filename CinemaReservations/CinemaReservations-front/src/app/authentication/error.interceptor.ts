import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpHeaders
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authorization = localStorage.getItem('currentUser') ? localStorage.getItem('currentUser') : '';
    let newrequest = request.clone({
      headers: new HttpHeaders({ 'Authorization': authorization })
    });

    return next.handle(newrequest).pipe(catchError( err => {
      if (err.status === 401) {
        // TODO auto logout if 401 with AuthService
        location.reload(true);
      }
      const error = err.error.message || err.statusText;
      return throwError(error);
    }));
  }
}
