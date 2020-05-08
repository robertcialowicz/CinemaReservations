import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {DataTableItem} from '../commons/data-table/data-table-datasource';

@Component({
  selector: 'app-shows',
  templateUrl: './shows.component.html',
  styleUrls: ['./shows.component.scss']
})
export class ShowsComponent implements OnInit {
  public show;
  public shows;

  constructor( private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.shows = this.route.snapshot.data['shows'];
    this.show = this.shows[0];
  }
  onshowSelected($event) {
    this.show = $event;
    window.scroll(0, 0 );
  }

  createShow() {
    this.show = {};
    window.scroll(0, 0 );
  }
}
