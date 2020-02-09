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
    event.detail.roomTypes.forEach(roomType => this.availableRooms.push(roomType));
  }

  reservationCreated(event) {
    this.router.navigateByUrl('reservation-details/' + event.detail)
  }
}
