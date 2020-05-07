import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataTableRowDetailsComponent } from './data-table-row-details.component';

describe('DataTableRowDetailsComponent', () => {
  let component: DataTableRowDetailsComponent;
  let fixture: ComponentFixture<DataTableRowDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataTableRowDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataTableRowDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
