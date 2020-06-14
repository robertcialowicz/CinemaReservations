import {AfterViewInit, ChangeDetectorRef, Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTable} from '@angular/material/table';
import {DataTableDataSource} from './reservations-table-datasource';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-reservations-table',
  templateUrl: './reservations-table.component.html',
  styleUrls: ['./reservations-table.component.scss']
})
export class ReservationsTableComponent implements OnInit, AfterViewInit {

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<any>;
  @Output() showSelected = new EventEmitter<any>();
  dataSource: DataTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['ReservationNumber', 'Film', 'Date', 'Email', 'Seats'];

  constructor(private route: ActivatedRoute, private changeDetectorRefs: ChangeDetectorRef) {}

  ngOnInit() {
    this.dataSource = new DataTableDataSource(this.route);
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;
  }

  rowClicked(row) {
    this.showSelected.emit(row);
  }

  refresh() {
    this.table.dataSource = this.dataSource;
  }

}
