import {Component, ViewChild, HostListener, OnInit} from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import {ApiService} from './services/api/api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent implements OnInit {
  constructor(private apiService: ApiService) {
  }

  ngOnInit() {
    // this.apiService.getFilms().subscribe(resp => {
    //   console.log(resp)
    // })
  }

}
