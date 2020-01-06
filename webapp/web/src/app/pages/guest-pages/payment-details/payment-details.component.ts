import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-payment-details',
  templateUrl: './payment-details.component.html',
  styleUrls: ['./payment-details.component.sass']
})
export class PaymentDetailsComponent implements OnInit {
  reservationId: string;

  constructor(private route: ActivatedRoute, private router: Router) {
    this.reservationId = this.route.snapshot.params['id'];
  }

  ngOnInit() {
  }

  holdAccepted() {
    this.router.navigateByUrl('/reservation-success/' + this.reservationId);
  }
}
