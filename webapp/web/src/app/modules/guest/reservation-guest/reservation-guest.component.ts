import {Component, Input, OnInit} from '@angular/core';
import {GuestService} from '../guest.service';
import {Observable} from 'rxjs';
import {Guest} from '../guest';
import {share} from 'rxjs/operators';

@Component({
  selector: 'app-guest',
  templateUrl: './reservation-guest.component.html',
  styleUrls: ['./reservation-guest.component.sass']
})
export class ReservationGuestComponent implements OnInit {
  @Input()
  reservationId: string;
  guest$: Observable<Guest>

  constructor(private guestService: GuestService) { }

  ngOnInit() {
    this.guest$ = this.guestService.findForReservation(this.reservationId).pipe(
      share()
    );
  }

}
