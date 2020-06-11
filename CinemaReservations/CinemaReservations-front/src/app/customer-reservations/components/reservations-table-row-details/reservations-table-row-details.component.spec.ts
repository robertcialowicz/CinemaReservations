import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReservationsTableRowDetailsComponent } from './reservations-table-row-details.component';

describe('ReservationsTableRowDetailsComponent', () => {
  let component: ReservationsTableRowDetailsComponent;
  let fixture: ComponentFixture<ReservationsTableRowDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReservationsTableRowDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReservationsTableRowDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
