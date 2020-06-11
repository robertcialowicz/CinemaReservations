import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import * as moment from 'moment';
@Component({
  selector: 'app-event-properties-modal',
  templateUrl: './event-properties-modal.component.html',
  styleUrls: ['./event-properties-modal.component.scss']
})
export class EventPropertiesModalComponent implements OnInit {
  public date: moment.Moment;
  public disabled = false;
  public showSpinners = true;
  public showSeconds = false;
  public touchUi = false;
  public enableMeridian = false;
  public minDate: moment.Moment;
  public maxDate: moment.Moment;
  public stepHour = 1;
  public stepMinute = 1;
  public stepSecond = 1;
  constructor(public dialogRef: MatDialogRef<EventPropertiesModalComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    dialogRef.disableClose = true;
  }

  ngOnInit(): void {}

  onNoClick(): void {
    this.dialogRef.close();
  }

}
