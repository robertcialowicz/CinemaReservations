import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

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

  constructor(private formBuilder: FormBuilder) { }

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
  }

  toggggleSelection(row: number, col: number) {
    // TODO
    const selector = '#seat-' + row + '-' + col;
    const seat = document.querySelector(selector);
    seat.classList.toggle('selected');
  }

}
