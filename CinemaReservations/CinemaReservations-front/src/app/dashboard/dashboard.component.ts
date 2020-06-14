import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  filmsCount: any;
  showsCount: any;
  reservationsCount: any;
  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.filmsCount = this.route.snapshot.data['filmsCount'];
    this.showsCount = this.route.snapshot.data['showsCount'];
    this.reservationsCount = this.route.snapshot.data['reservationsCount'];
  }

}
