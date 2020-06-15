import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../services/api/api.service';
import * as moment from 'moment';

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
  seatsNumberToPick = 0;
  sumamry: any;
  films: any
  event: any;
  originalEvent: any

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private apiService: ApiService, private router: Router) {}

  ngOnInit(): void {
    this.films = this.route.snapshot.data['films'];
    this.route.queryParams.subscribe(params => {
      this.apiService.getShow(params['eventId']).subscribe((res: any) => {
        this.originalEvent = { ...res};
        res.date = moment(res.date, "x").format("LLLL");
        res.film = this.films.find(el => el.id == res.film )
        this.event = res;
      });
    })

    this.firstFormGroup = this.formBuilder.group({
      firstCtrl: ['', Validators.required]
    });
    this.secondFormGroup = this.formBuilder.group({
      bookedSeats: ['']
    });
    this.thirdFormGroup = this.formBuilder.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      email: ['', Validators.compose([Validators.required, Validators.email])]
    });
  }

  toggggleSelection(id: any) {
    const selector = '#' + id;
    const seat = document.querySelector(selector);
    if(seat.classList.contains('disabled')) return;
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

  document.querySelectorAll('div.selected[id^=seat]').forEach(el => {
    el.classList.toggle('selected');
  })

  if(this.event.seatsReservationStatus) {
    this.event.seatsReservationStatus.split(',').forEach(seat => {
      seat = seat.trim();
      if(seat !== '') document.querySelector('#' + seat).classList.add('disabled');
    })
  }

    this.seatsNumberToPick = this.firstFormGroup.controls.firstCtrl.value;
  }

  setSelectedSeats() {
    const seatsString = Array<String>();
    document.querySelectorAll('div.selected').forEach(el => {
      seatsString.push(el.id)
    })
    this.secondFormGroup.controls.bookedSeats.setValue(seatsString.join(','))
  }

  createSummary() {
    this.sumamry = Object.assign({}, this.secondFormGroup.value, this.thirdFormGroup.value, {movieShow: this.originalEvent.id})
    console.log(this.secondFormGroup.value)
    console.log(this.thirdFormGroup.value)
    console.log(this.sumamry)

  }

  navigateToPayment() {
    this.apiService.postReservation(this.sumamry).subscribe(resp => {
    })
    this.router.navigate(['/cinema-reservations'])
    window.open('http://www.partridgegetslucky.com/');
  }

}
