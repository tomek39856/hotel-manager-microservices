import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ReservationService} from "../../services/reservation.service";

@Component({
  selector: 'app-confirm-arrival',
  templateUrl: './confirm-arrival.component.html',
  styleUrls: ['./confirm-arrival.component.sass']
})
export class ConfirmArrivalComponent implements OnInit {
  @Input()
  reservation_id: string;
  @Output()
  arrival_confirmed = new EventEmitter<void>()

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
  }

  confirmArrival() {
    this.reservationService.confirmArrival(this.reservation_id).subscribe(
      v => this.arrival_confirmed.emit()
    );
  }
}
