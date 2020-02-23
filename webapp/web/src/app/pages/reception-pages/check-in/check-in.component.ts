import { Component, OnInit } from '@angular/core';
import {BehaviorSubject, Subject} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {Reservation} from "../../../model/reservation";
import {Room} from "../../../model/room";

@Component({
  selector: 'app-check-in',
  templateUrl: './check-in.component.html',
  styleUrls: ['./check-in.component.sass']
})
export class CheckInComponent implements OnInit {
  reservationId: string;
  guestId: string;
  reservationSubject: Subject<Reservation> = new BehaviorSubject(null);
  rooms: Room[] = [];
  reservation: Reservation;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.reservationId = this.route.snapshot.params['reservationId'];
    this.guestId = this.route.snapshot.params['guestId'];
  }

  reservationFound(reservationFoundEvent) {
    this.reservationSubject.next(reservationFoundEvent.detail);
  }

  roomsFound(roomsFoundEvent) {
    this.rooms = roomsFoundEvent.detail;
  }
}
