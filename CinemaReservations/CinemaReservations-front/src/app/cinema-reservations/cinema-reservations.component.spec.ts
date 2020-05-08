import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaReservationsComponent } from './cinema-reservations.component';

describe('CinemaReservationsComponent', () => {
  let component: CinemaReservationsComponent;
  let fixture: ComponentFixture<CinemaReservationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CinemaReservationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CinemaReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
