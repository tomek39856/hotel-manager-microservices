import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {share} from 'rxjs/operators';
import {Guest} from "../../model/guest";
import {GuestService} from "../../services/guest.service";

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
