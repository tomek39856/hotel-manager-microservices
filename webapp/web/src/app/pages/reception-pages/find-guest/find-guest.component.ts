import { Component, OnInit } from '@angular/core';
import {Guest} from '../../../modules/guest/guest';
import {Router} from '@angular/router';

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

  guestsFound(guests: Guest[]) {
    this.guests = guests;
  }

  guestArrivalConfirmed(guest: Guest) {
    this.router.navigateByUrl('/reception/check-in/' + guest.id + '/reservation/' + guest.reservationId);
  }
}
