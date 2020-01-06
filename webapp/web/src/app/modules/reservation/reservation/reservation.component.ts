import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Observable} from 'rxjs';
import {Reservation} from '../reservation';
import {ReservationService} from '../reservation.service';
import {share} from 'rxjs/operators';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.sass']
})
  export class ReservationComponent implements OnInit {
  @Input()
  reservationId: string;
  @Output()
  reservationFound: EventEmitter<Reservation> = new EventEmitter<Reservation>()
  reservation$: Observable<Reservation>;

  constructor(private reservationService: ReservationService) { }

  ngOnInit() {
    this.reservation$ = this.reservationService.getById(this.reservationId).pipe(share());
    this.reservation$.subscribe(value => this.reservationFound.emit(value));
  }
}
