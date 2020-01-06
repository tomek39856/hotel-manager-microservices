import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {RoomsAvailableEvent} from '../../../modules/reservation/avaliable-search/rooms-available-event';
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

  roomsAvailable(event: RoomsAvailableEvent) {
    this.availableRooms = event.roomTypes;
    this.from = event.from;
    this.to = event.to;
  }

  reservationCreated(reservationId: string) {
    this.router.navigateByUrl('reservation-details/' + reservationId)
  }
}
