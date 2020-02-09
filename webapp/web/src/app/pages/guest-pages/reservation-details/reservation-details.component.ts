import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {combineLatest, forkJoin, Subject} from 'rxjs';

@Component({
  selector: 'app-reservation-details',
  templateUrl: './reservation-details.component.html',
  styleUrls: ['./reservation-details.component.sass']
})
export class ReservationDetailsComponent implements OnInit {
  reservationId: string;
  private submitEventsSubject: Subject<void> = new Subject<void>();
  private paymentCreatedSubject: Subject<string> = new Subject<string>();
  private guestCreatedSubject: Subject<void> = new Subject<void>();

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.reservationId = this.route.snapshot.params['id'];
    combineLatest(this.paymentCreatedSubject, this.guestCreatedSubject).subscribe(
      value => {
        this.router.navigateByUrl('/payment-details/' + this.reservationId);
      }
    )
  }

  submit() {
    this.submitEventsSubject.next();
  }

  paymentCreated(event) {
    console.log('payment created received '+ event.detail)
    this.paymentCreatedSubject.next(event.detail);
  }

  guestCreated() {
    console.log('reservation-guest created received')
    this.guestCreatedSubject.next();
  }

}
