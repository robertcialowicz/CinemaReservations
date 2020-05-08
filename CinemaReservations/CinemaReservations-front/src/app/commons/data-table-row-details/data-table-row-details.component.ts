import {AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent} from '@angular/material/chips';
import {MatDatepickerInputEvent} from '@angular/material/datepicker';

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
  defaultShow: any;
  actualMode = showDetailsMode.displayMode;
  showItemDetails: FormGroup;

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];
  constructor( private formBuilder: FormBuilder) {
    this.showItemDetails = this.formBuilder.group({
      screeningData: this.formBuilder.group({
        screeningTime: ['',  Validators.required],
        screeningStartPeriod: ['',  Validators.required],
        screeningEndPeriod: ['',  Validators.required]
      }),
      generalData: this.formBuilder.group({
        id: ['',  Validators.required],
        name: ['',  Validators.required],
        type: [''],
        director: [''],
        scenario: [''],
        releaseDate: [''],
        description: [''],
        cast: this.formBuilder.array([]),
        ageLimit: ['']
      })
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
        screeningData: {
          screeningTime: 0,
          screeningStartPeriod: 0,
          screeningEndPeriod: 0,
        },
        generalData: {
          id: null,
          name: '',
          type: [],
          director: '',
          scenario: '',
          releaseDate: null,
          description: '',
          cast: [],
          ageLimit: null,
        }
      };
      return this.switchToModeCreate();

    }
    this.actualMode = showDetailsMode.displayMode;
  }

  add(event: MatChipInputEvent): void {
    console.log(event)
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
      generalData: {
        name: this.show.generalData.name,
        type: this.show.generalData.name,
        director: this.show.generalData.director,
        scenario: this.show.generalData.scenario,
        releaseDate: this.show.generalData.releaseDate,
        description: this.show.generalData.description,
        ageLimit: this.show.generalData.ageLimit
      },
      screeningData: {
        screeningTime: this.show.screeningData.screeningTime,
        screeningStartPeriod: this.show.screeningData.screeningStartPeriod,
        screeningEndPeriod: this.show.screeningData.screeningEndPeriod
      }
    });

    const formArray = (this.showItemDetails.get('generalData.cast') as FormArray);
    while (formArray.length !== 0) {
      formArray.removeAt(0);
    }
    this.show.generalData.cast.forEach(item => {
      formArray.push(this.formBuilder.group({person: item}));
    });
  }

  saveChanges() {
    if (this.actualMode === 1)  {
      // TODO send created show details
    }
    // TODO send update show details
    console.log(this.showItemDetails);
  }

  discardChanges() {
    if (this.actualMode === 1)  {
      this.show = this.defaultShow;
    }
    this.actualMode = showDetailsMode.displayMode;
  }

}
