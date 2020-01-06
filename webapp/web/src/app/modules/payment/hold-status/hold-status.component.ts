import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PaymentService} from '../payment.service';
import {BehaviorSubject, interval, of, Subject, timer} from 'rxjs';
import {shareReplay, switchMap, takeWhile, tap} from 'rxjs/operators';

@Component({
  selector: 'app-hold-status',
  templateUrl: './hold-status.component.html',
  styleUrls: ['./hold-status.component.sass']
})
export class HoldStatusComponent implements OnInit {
  chargeStatus$: Subject<string> = new BehaviorSubject(null);
  @Input()
  reservationId: string;
  @Output()
  holdFailed: EventEmitter<void> = new EventEmitter<void>();
  @Output()
  holdAccepted: EventEmitter<void> = new EventEmitter<void>();

  constructor(private paymentService: PaymentService) { }

  ngOnInit() {
    timer(0, 5000).pipe(
      switchMap(() => this.paymentService.getStatus(this.reservationId)),
      tap((status) => this.chargeStatus$.next(status)),
      tap((status) => this.notifyAboutHoldStatus(status)),
      switchMap((status) => of(status === 'NEW')),
      takeWhile(val => val, true),
      shareReplay(1)
    ).subscribe();
  }

  private notifyAboutHoldStatus(status: string) {
    if(status === 'HOLD') {
      this.holdAccepted.emit();
    } else if(status === 'FAILED') {
      this.holdFailed.emit();
    }
  }

}
