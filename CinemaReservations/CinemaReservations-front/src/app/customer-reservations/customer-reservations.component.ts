import {Component, OnInit, ViewChild} from '@angular/core';
import {DataTableComponent} from '../commons/data-table/data-table.component';
import {ActivatedRoute} from '@angular/router';
import {ApiService} from '../services/api/api.service';

@Component({
  selector: 'app-customer-reservations',
  templateUrl: './customer-reservations.component.html',
  styleUrls: ['./customer-reservations.component.scss']
})
export class CustomerReservationsComponent implements OnInit {

  public show;
  public shows;

  @ViewChild(DataTableComponent) dataTableComponent: DataTableComponent;

  constructor( private route: ActivatedRoute, private apiService: ApiService) {
  }

  ngOnInit(): void {
    this.shows = this.route.snapshot.data['reservations'];
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
