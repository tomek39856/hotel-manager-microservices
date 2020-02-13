import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-reservation-success',
  templateUrl: './reservation-success.component.html',
  styleUrls: ['./reservation-success.component.sass']
})
export class ReservationSuccessComponent implements OnInit {
  reservationId: string;

  constructor(private route: ActivatedRoute) {
    this.reservationId = this.route.snapshot.params['id'];
    console.log('route snapshot:' + this.reservationId)
  }

  ngOnInit() {

  }

}
