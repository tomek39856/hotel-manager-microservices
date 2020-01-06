import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BehaviorSubject, of, Subject, timer} from 'rxjs';
import {PaymentService} from '../payment.service';
import {shareReplay, switchMap, takeWhile, tap} from 'rxjs/operators';

@Component({
  selector: 'app-payment-status',
  templateUrl: './payment-status.component.html',
  styleUrls: ['./payment-status.component.sass']
})
export class PaymentStatusComponent implements OnInit {
  chargeStatus$: Subject<string> = new BehaviorSubject(null);
  @Input()
  reservationId: string;
  @Output()
  paymentFailed: EventEmitter<void> = new EventEmitter<void>();
  @Output()
  paymentAccepted: EventEmitter<void> = new EventEmitter<void>();

  constructor(private paymentService: PaymentService) { }

  ngOnInit() {
    timer(0, 5000).pipe(
      switchMap(() => this.paymentService.getStatus(this.reservationId)),
      tap((status) => this.chargeStatus$.next(status)),
      tap((status) => this.notifyAboutPaymentStatus(status)),
      switchMap((status) => of(status != 'CHARGED' && status != 'FAILED')),
      takeWhile(val => val, true),
      shareReplay(1)
    ).subscribe();
  }

  private notifyAboutPaymentStatus(status: string) {
    if(status === 'HOLD') {
      this.paymentAccepted.emit();
    } else if(status === 'FAILED') {
      this.paymentFailed.emit();
    }
  }

}
