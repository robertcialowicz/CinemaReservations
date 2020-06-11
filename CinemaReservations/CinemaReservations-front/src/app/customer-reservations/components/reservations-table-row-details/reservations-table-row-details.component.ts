import {AfterViewInit, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {ApiService} from '../../../services/api/api.service';
import {MatChipInputEvent} from '@angular/material/chips';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import {showDetailsMode} from '../../../commons/data-table-row-details/data-table-row-details.component';

@Component({
  selector: 'app-reservations-table-row-details',
  templateUrl: './reservations-table-row-details.component.html',
  styleUrls: ['./reservations-table-row-details.component.scss']
})
export class ReservationsTableRowDetailsComponent implements OnInit, AfterViewInit, OnChanges {

  @Input() show: any;
  @Output() showUpdated = new EventEmitter<any>();
  defaultShow: any;
  actualMode = showDetailsMode.displayMode;
  reservationItemDetails: FormGroup;

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  constructor( private formBuilder: FormBuilder, private apiService: ApiService) {
    this.reservationItemDetails = this.formBuilder.group({
      id: [{value: '', disabled: true},  Validators.required],
      film: [{value: '', disabled: true},  Validators.required],
      date: [{value: '', disabled: true},  Validators.required],
      hall: [{value: '', disabled: true},  Validators.required],
      email: [{value: '', disabled: true},  Validators.required],
      seats: this.formBuilder.array([])
    });
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.defaultShow = this.show;
    this.setFormValues();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (Object.keys(changes.show.currentValue).length === 0) {
      this.show = {
        id: null,
        film: '',
        date: '',
        hall: '',
        email: '',
        seats: []
      };
      return this.switchToModeCreate();

    }
    this.actualMode = showDetailsMode.displayMode;
  }

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;
    //
    // Add name
    if ((value || '').trim()) {
      (this.reservationItemDetails.get('seats') as FormArray).push(this.formBuilder.group({seat: value}));
    }
    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(index) {
    (this.reservationItemDetails.get('seats') as FormArray).removeAt(index);
  }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    console.log(event)
  }

  switchToModeEdit(show: any) {
    this.show = show;
    this.setFormValues();
    this.actualMode = showDetailsMode.editMode;
  }

  editSeats(show) {
    console.log(show)
  }

  switchToModeCreate() {
    this.setFormValues();
    this.actualMode = showDetailsMode.createMode;
  }

  private setFormValues() {
    this.reservationItemDetails.patchValue({
      id: this.show.id,
      film: this.show.film,
      date: this.show.date,
      hall: this.show.hall,
      email: this.show.email
    });

    const formArray = (this.reservationItemDetails.get('seats') as FormArray);
    while (formArray.length !== 0) {
      formArray.removeAt(0);
    }
    this.show.seats.forEach(item => {
      formArray.push(this.formBuilder.group({seat: item}));
    });
  }

  saveChanges() {
    // this.apiService.postShows(this.reservationItemDetails.value).subscribe(rep => {
    //   this.showUpdated.emit(true);
    //   this.show = rep.find(el => {
    //     return el.id === this.reservationItemDetails.value.id;
    //   });
    //   this.actualMode = showDetailsMode.displayMode;
    // })
  }

  discardChanges() {
    if (this.actualMode === 1)  {
      this.show = this.defaultShow;
    }
    this.actualMode = showDetailsMode.displayMode;
  }

}
