import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-room-selection',
  templateUrl: './room-selection.component.html',
  styleUrls: ['./room-selection.component.sass'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class RoomSelectionComponent implements OnInit {
  availableRooms: string[] = [];
  from: string;
  to: string;

  constructor(private router: Router) { }

  ngOnInit() {}

  roomsAvailable(event) {

    this.from = event.detail.from;
    this.to = event.detail.to;
    this.availableRooms = event.detail.roomTypes;
  }

  reservationCreated(reservationId: string) {
    this.router.navigateByUrl('reservation-details/' + reservationId)
  }
}
