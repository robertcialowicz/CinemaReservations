import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CinemaEventsComponent } from './cinema-events.component';

describe('CalendarOfEventsComponent', () => {
  let component: CinemaEventsComponent;
  let fixture: ComponentFixture<CinemaEventsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CinemaEventsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CinemaEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
