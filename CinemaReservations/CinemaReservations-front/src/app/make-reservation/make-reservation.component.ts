import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute} from '@angular/router';
import {ApiService} from '../services/api/api.service';

@Component({
  selector: 'app-make-reservation',
  templateUrl: './make-reservation.component.html',
  styleUrls: ['./make-reservation.component.scss']
})
export class MakeReservationComponent implements OnInit {
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  numberOfRows = Array<any>(10).fill(1).map((x, i) => i + 1);
  namesOfRows = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'];
  numberOfColsLeft = Array<any>(10).fill(1).map((x, i) => i + 1);
  numberOfColsRight = Array<any>(10).fill(10).map((x, i) => i + 10 + 1);
  seatsNumberToPick: any;

  event: any;

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private apiService: ApiService) { }

  ngOnInit(): void {
    // TODO
    this.firstFormGroup = this.formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this.formBuilder.group({
      secondCtrl: ['']
    });
    this.thirdFormGroup = this.formBuilder.group({
      thirdCtrl: ['', Validators.required]
    });

    this.apiService.getEvent(this.route.queryParams['eventId']).subscribe(res => {
      this.event = res;
    });
  }

  toggggleSelection(row: number, col: number) {
    const selector = '#seat-' + row + '-' + col;
    const seat = document.querySelector(selector);
    if(this.seatsNumberToPick) {
      seat.classList.contains('selected') ? this.seatsNumberToPick += 1 : this.seatsNumberToPick -= 1;
      seat.classList.toggle('selected');
    } else {
      if(seat.classList.contains('selected')) {
        this.seatsNumberToPick += 1
        seat.classList.toggle('selected');
      }
    }
  }

  setSeatsNumberToPick() {
    const selectedSeats = document.querySelectorAll('div.selected[id^=seat]').forEach(el => {
      el.classList.toggle('selected');
    })
    this.seatsNumberToPick = this.firstFormGroup.controls.firstCtrl.value;
  }

}
