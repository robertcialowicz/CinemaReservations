import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CinemaEventsComponent} from './cinema-events/cinema-events.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {ShowsComponent} from './shows/shows.component';
import {
  FilmsResolversService, HallsResolverService, ReservationsResolverService, ShowsResolversService,
} from './services/resolvers/resolvers.service';
import {AuthGuard} from './authentication/auth.guard';
import {LoginComponent} from './authentication/login/login.component';
import {NavigationComponent} from './navigation/navigation.component';
import {RepertoireComponent} from './repertoire/repertoire.component';
import {CinemaReservationsComponent} from './cinema-reservations/cinema-reservations.component';
import {CustomerReservationsComponent} from './customer-reservations/customer-reservations.component';
import {MakeReservationComponent} from './make-reservation/make-reservation.component';


const routes: Routes = [

  { path: 'login', component: LoginComponent },
  { path: '', pathMatch: 'full', redirectTo: 'administrator-panel' },
  { path: 'cinema-reservations', component: CinemaReservationsComponent,
    children: [
      { path: 'repertoire', component: RepertoireComponent, resolve: { shows: ShowsResolversService, films: FilmsResolversService } },
      { path: 'make-reservation', component: MakeReservationComponent },
      { path: '', pathMatch: 'full',  redirectTo: 'repertoire' },
    ]},
  { path: 'administrator-panel', component: NavigationComponent, canActivate: [AuthGuard],
    children: [
      { path: '', pathMatch: 'full',  redirectTo: 'dashboard' },
      { path: 'cinema-events', component: CinemaEventsComponent, resolve: { shows: ShowsResolversService, films: FilmsResolversService, halls: HallsResolverService }},
      { path: 'dashboard', component: DashboardComponent},
      { path: 'shows', component: ShowsComponent, resolve: { films: FilmsResolversService } },
      { path: 'customer-reservations', component: CustomerReservationsComponent,  resolve: { reservations: ReservationsResolverService } }
]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
