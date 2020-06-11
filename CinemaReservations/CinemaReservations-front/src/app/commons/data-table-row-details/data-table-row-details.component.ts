import {AfterViewInit, Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent} from '@angular/material/chips';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';
import {ApiService} from '../../services/api/api.service';

export enum showDetailsMode {
  editMode = 0,
  createMode = 1,
  displayMode = 2,
}

@Component({
  selector: 'app-data-table-row-details',
  templateUrl: './data-table-row-details.component.html',
  styleUrls: ['./data-table-row-details.component.scss']
})
export class DataTableRowDetailsComponent implements OnInit, AfterViewInit, OnChanges {
  @Input() show: any;
  @Output() showUpdated = new EventEmitter<any>();
  defaultShow: any;
  actualMode = showDetailsMode.displayMode;
  showItemDetails: FormGroup;

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  constructor( private formBuilder: FormBuilder, private apiService: ApiService) {
    this.showItemDetails = this.formBuilder.group({
      id: [null],
      title: ['',  Validators.required],
      description: ['']
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
        title: '',
        description: ''
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
      (this.showItemDetails.get('generalData.cast') as FormArray).push(this.formBuilder.group({person: value}));
    }
    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(index) {
    (this.showItemDetails.get('generalData.cast') as FormArray).removeAt(index);
  }

  addEvent(type: string, event: MatDatepickerInputEvent<Date>) {
    console.log(event)
  }

  switchToModeEdit(show: any) {
    this.show = show;
    this.setFormValues();
    this.actualMode = showDetailsMode.editMode;
  }

  switchToModeCreate() {
    this.setFormValues();
    this.actualMode = showDetailsMode.createMode;
  }

  private setFormValues() {
    this.showItemDetails.patchValue({
      id: this.show.id,
      title: this.show.title,
      description: this.show.description
    });

    // const formArray = (this.showItemDetails.get('generalData.cast') as FormArray);
    // while (formArray.length !== 0) {
    //   formArray.removeAt(0);
    // }
    // this.show.generalData.cast.forEach(item => {
    //   formArray.push(this.formBuilder.group({person: item}));
    // });
  }

  saveChanges() {
    Object.assign(this.showItemDetails.value, {movieShows: []})
    this.apiService.postFilms(this.showItemDetails.value).subscribe(rep => {
      this.apiService.getFilms().subscribe((rep1: any) => {
        this.showUpdated.emit(true);
        this.show =  rep1.find(el => {
          return el.title === this.showItemDetails.value.title;
        });
        this.actualMode = showDetailsMode.displayMode;
      });
    });
  }

  discardChanges() {
    if (this.actualMode === 1)  {
      this.show = this.defaultShow;
    }
    this.actualMode = showDetailsMode.displayMode;
  }

}
