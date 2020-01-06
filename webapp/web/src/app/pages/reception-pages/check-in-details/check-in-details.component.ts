import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-check-in-details',
  templateUrl: './check-in-details.component.html',
  styleUrls: ['./check-in-details.component.sass']
})
export class CheckInDetailsComponent implements OnInit {
  reservationId: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.reservationId = this.route.snapshot.params['reservationId'];
  }

}
