import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ReservationService} from '../reservation.service';

@Component({
  selector: 'app-confirm-arrival',
  templateUrl: './confirm-arrival.component.html',
  styleUrls: ['./confirm-arrival.component.sass']
})
export class ConfirmArrivalComponent implements OnInit {
  @Input()
  reservationId: string;
  @Output()
  arrivalConfirmed = new EventEmitter<void>()

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
  }

  confirmArrival() {
    this.reservationService.confirmArrival(this.reservationId).subscribe(
      v => this.arrivalConfirmed.emit()
    );
  }
}
