import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ReservationService} from "../../services/reservation.service";

@Component({
  selector: 'app-reserve',
  templateUrl: './reserve.component.html',
  styleUrls: ['./reserve.component.sass']
})
export class ReserveComponent implements OnInit {
  @Input()
  room_type: string;
  @Input()
  from: string;
  @Input()
  to: string;
  @Output('reservation-created')
  reservationCreated: EventEmitter<string> = new EventEmitter<string>();

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
  }

  reserve() {
    this.reservationService.reserve(this.room_type, this.from, this.to).subscribe(
      value => this.reservationCreated.emit(value)
    )
  }

}
