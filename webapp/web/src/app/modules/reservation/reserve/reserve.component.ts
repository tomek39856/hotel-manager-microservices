import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ReservationService} from '../reservation.service';

@Component({
  selector: 'app-reserve',
  templateUrl: './reserve.component.html',
  styleUrls: ['./reserve.component.sass']
})
export class ReserveComponent implements OnInit {
  @Input()
  roomType: string;
  @Input()
  from: string;
  @Input()
  to: string;
  @Output()
  reservationCreated: EventEmitter<string> = new EventEmitter<string>();

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
  }

  reserve() {
    this.reservationService.reserve(this.roomType, this.from, this.to).subscribe(
      value => this.reservationCreated.emit(value)
    )
  }

}
