import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationComponent } from './navigation/navigation.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatGridListModule} from '@angular/material/grid-list';
import { CinemaEventsComponent } from './cinema-events/cinema-events.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import { DashboardComponent } from './dashboard/dashboard.component';
import {MatCardModule} from '@angular/material/card';
import { ShowsComponent } from './shows/shows.component';
import { DataTableComponent } from './commons/data-table/data-table.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTooltipModule } from '@angular/material/tooltip';
import { DataTableRowDetailsComponent } from './commons/data-table-row-details/data-table-row-details.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatChipsModule} from '@angular/material/chips';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import { LoginComponent } from './authentication/login/login.component';
import {ErrorInterceptor} from './authentication/error.interceptor';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {fakeBackendProvider} from './authentication/fake-backend.interceptor';
import {MatTabsModule} from '@angular/material/tabs';
import {MatExpansionModule} from '@angular/material/expansion';
import { RepertoireComponent } from './repertoire/repertoire.component';
import { CinemaReservationsComponent } from './cinema-reservations/cinema-reservations.component';
import {CarouselModule} from 'ngx-owl-carousel-o';
import {MatDialogModule} from '@angular/material/dialog';
import { CustomerReservationsComponent } from './customer-reservations/customer-reservations.component';
import { MakeReservationComponent } from './make-reservation/make-reservation.component';
import {MatStepperModule} from '@angular/material/stepper';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    CinemaEventsComponent,
    DashboardComponent,
    ShowsComponent,
    DataTableComponent,
    DataTableRowDetailsComponent,
    LoginComponent,
    RepertoireComponent,
    CinemaReservationsComponent,
    CustomerReservationsComponent,
    MakeReservationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    BrowserAnimationsModule,
    MatGridListModule,
    FullCalendarModule,
    MatCardModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatTooltipModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatChipsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    HttpClientModule,
    MatTabsModule,
    MatExpansionModule,
    CarouselModule,
    MatDialogModule,
    MatStepperModule
  ],
  providers: [
    MatDatepickerModule,
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    // provider used to create fake backend
    fakeBackendProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
