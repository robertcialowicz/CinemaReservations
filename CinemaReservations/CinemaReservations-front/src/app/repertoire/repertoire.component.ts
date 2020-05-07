import { AfterViewInit, Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-repertoire',
  templateUrl: './repertoire.component.html',
  styleUrls: ['./repertoire.component.scss']
})
export class RepertoireComponent implements OnInit, AfterViewInit {
  repertoire: any;

  // customOptions: OwlOptions = {
  //   loop: true,
  //   center: true,
  //   dots: true,
  //   responsive: {
  //     0: {
  //       items: 1,
  //     },
  //     1: {
  //       items: 1,
  //     },
  //     2: {
  //       items: 2,
  //     },
  //     3: {
  //       items: 3,
  //     },
  //     4: {
  //       items: 3,
  //     },
  //     400: {
  //       items: 2
  //     },
  //     740: {
  //       items: 3
  //     },
  //     940: {
  //       items: 4
  //     }
  //   }
  // }

  constructor(
    private route: ActivatedRoute, public dialog: MatDialog,
    private router: Router) {
  }

  ngOnInit(): void {
    this.repertoire = this.route.snapshot.data['repertoire'];
  }

  ngAfterViewInit(): void {
  }

  // openDialog(slide: any): void {
  //   const dialogRef = this.dialog.open(DialogComponent, {
  //     width: '250px',
  //     data: {slide}
  //   });
  //
  //   dialogRef.afterClosed().subscribe(result => {
  //     console.log(result);
  //   });
  // }

  makeReservation(showId: any) {
    this.router.navigate(['/cinema-reservations/make-reservation'], {queryParams: { showId}});
  }
}

