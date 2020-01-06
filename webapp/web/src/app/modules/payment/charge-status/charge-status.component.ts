import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BehaviorSubject, of, Subject, timer} from 'rxjs';
import {PaymentService} from '../payment.service';
import {shareReplay, switchMap, takeWhile, tap} from 'rxjs/operators';

@Component({
  selector: 'app-charge-status',
  templateUrl: './charge-status.component.html',
  styleUrls: ['./charge-status.component.sass']
})
export class ChargeStatusComponent implements OnInit {
  chargeStatus$: Subject<string> = new BehaviorSubject(null);
  @Input()
  reservationId: string;

  constructor(private paymentService: PaymentService) { }

  ngOnInit() {
    timer(0, 5000).pipe(
      switchMap(() => this.paymentService.getStatus(this.reservationId)),
      tap((status) => this.chargeStatus$.next(status)),
      switchMap((status) => of(status === 'NEW')),
      takeWhile(val => val, true),
      shareReplay(1)
    ).subscribe();
  }
}
