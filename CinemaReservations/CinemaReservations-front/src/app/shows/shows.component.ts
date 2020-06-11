import { Component, OnInit,ViewChild } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {DataTableItem} from '../commons/data-table/data-table-datasource';
import {ApiService} from '../services/api/api.service';
import {DataTableComponent} from '../commons/data-table/data-table.component';

@Component({
  selector: 'app-shows',
  templateUrl: './shows.component.html',
  styleUrls: ['./shows.component.scss']
})
export class ShowsComponent implements OnInit {
  public show;
  public shows;

  @ViewChild(DataTableComponent) dataTableComponent: DataTableComponent;

  constructor( private route: ActivatedRoute, private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.shows = this.route.snapshot.data['films'];
    this.show = this.shows[0];
  }
  onshowSelected($event) {
    this.show = $event;
    window.scroll(0, 0 );
  }

  onShowUpdated() {
    this.apiService.getShows().subscribe(res => {
      this.dataTableComponent.dataSource.data = <any>res;
      this.dataTableComponent.refresh()
    })
  }

  createShow() {
    this.show = {};
    window.scroll(0, 0 );
  }
}
