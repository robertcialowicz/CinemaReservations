import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpResponse, HTTP_INTERCEPTORS
} from '@angular/common/http';
import {Observable, of, throwError} from 'rxjs';
import {delay, dematerialize, materialize, mergeMap} from 'rxjs/operators';

@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const users: User[] = [
      {id: 1, username: 'test', password: 'test', firstName: 'Test', lastName: 'User'}
    ];

    const authHeader = request.headers.get('Authorization');
    const isLoggedIn = authHeader && authHeader.startsWith('Bearer fake-jwt-token');

    return of(null).pipe(mergeMap(() => {
      if (request.url.endsWith(('users/authenticate')) && request.method === 'POST') {
        const user = users.find(x => x.username === request.body.username && x.password === request.body.password);
        if (!user) {
          return error('Username or password is incorrect');
        }
        return ok({
          id: user.id,
          username: user.username,
          firstName: user.firstName,
          lastName: user.lastName,
          token: `fake-jwt-token`
        });
      }

      if (request.url.endsWith('/users') && request.method === 'GET') {
        if (!isLoggedIn) {
          return unauthorised();
        }
        ;
        return ok(users);
      }

      // pass through any requests not handled above
      return next.handle(request);
    }))
      .pipe(materialize())
      .pipe(delay(500))
      .pipe(dematerialize());

    function error(message) {
      return throwError({status: 400, error: {message}});
    }

    function ok(body) {
       return of(new HttpResponse({status: 200, body}));
    }

    function unauthorised() {
      return throwError({status: 401, error: {message: 'Unauthorised'}});
    }
  }
}

export let fakeBackendProvider = {
  // use fake backend in place of Http service for backend-less development
  provide: HTTP_INTERCEPTORS,
  useClass: FakeBackendInterceptor,
  multi: true
};

export class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
  token?: string;
}
