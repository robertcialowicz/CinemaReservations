import {AfterViewInit, ChangeDetectorRef, Component, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { DataTableDataSource, DataTableItem } from './data-table-datasource';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.scss']
})
export class DataTableComponent implements AfterViewInit, OnInit {
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  @ViewChild(MatTable) table: MatTable<DataTableItem>;
  @Output() showSelected = new EventEmitter<any>();
  dataSource: DataTableDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  // displayedColumns = ['name', 'type', 'director', 'scenario', 'releaseDate', 'description'];
  displayedColumns = ['name', 'type', 'director', 'scenario', 'releaseDate', 'description'];

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
