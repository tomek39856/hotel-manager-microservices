import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  EventEmitter,
  Input,
  OnChanges,
  Output,
  SimpleChanges
} from '@angular/core';
import {Observable} from 'rxjs';
import {share} from 'rxjs/operators';
import {Reservation} from "../../model/reservation";
import {ReservationService} from "../../services/reservation.service";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.sass']
})
  export class ReservationComponent implements OnChanges {
  @Input()
  reservation_id: string;
  @Output()
  reservation_found: EventEmitter<Reservation> = new EventEmitter<Reservation>()
  reservation: Reservation;

  constructor(private reservationService: ReservationService, private changeDetectorRef: ChangeDetectorRef) { }

  ngOnInit() {
    console.log('init:' + this.reservation_id)
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.reservationService.getById(this.reservation_id)
    .subscribe(value => {
      this.reservation = value;
      this.reservation_found.emit(value);
      this.changeDetectorRef.detectChanges();
    });

  }
}
