import { Component, OnInit } from '@angular/core';
import {BehaviorSubject, Subject} from 'rxjs';
import {Reservation} from '../../../modules/reservation/reservation';
import {ActivatedRoute} from '@angular/router';
import {Room} from '../../../modules/occupancy/room';

@Component({
  selector: 'app-check-in',
  templateUrl: './check-in.component.html',
  styleUrls: ['./check-in.component.sass']
})
export class CheckInComponent implements OnInit {
  reservationId: string;
  guestId: string;
  reservationSubject: Subject<Reservation> = new BehaviorSubject(null);
  roomsSubject: Subject<Room[]> = new BehaviorSubject(null);
  rooms: Room[] = [];

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.reservationId = this.route.snapshot.params['reservationId'];
    this.guestId = this.route.snapshot.params['guestId'];
  }

  reservationFound(reservation: Reservation) {
    this.reservationSubject.next(reservation);
  }

  roomsFound(rooms: Room[]) {
    this.rooms = rooms;
  }

}
