import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Guest} from "../../../model/guest";

@Component({
  selector: 'app-find-guest',
  templateUrl: './find-guest.component.html',
  styleUrls: ['./find-guest.component.sass']
})
export class FindGuestComponent implements OnInit {
  guests: Guest[];

  constructor(private router: Router) { }

  ngOnInit() {
  }

  guestsFound(guestsEvent) {
    this.guests = guestsEvent.detail;
  }

  guestArrivalConfirmed(guest: Guest) {
    this.router.navigateByUrl('/reception/check-in/' + guest.id + '/reservation/' + guest.reservationId);
  }
}
