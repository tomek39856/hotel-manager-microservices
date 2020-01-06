import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Room} from '../room';
import {RoomService} from '../room.service';

@Component({
  selector: 'app-room-check-in',
  templateUrl: './room-check-in.component.html',
  styleUrls: ['./room-display.component.sass']
})
export class RoomCheckInComponent implements OnInit {
  @Input()
  rooms: Room[];
  @Input()
  from: string;
  @Input()
  to: string;
  @Input()
  reservationId: string;
  @Output()
  checkedIn: EventEmitter<void> = new EventEmitter<void>();

  constructor(private roomService: RoomService) { }

  ngOnInit() {
  }

  checkIn(room: Room) {
    console.log(room);
    this.roomService.checkIn(this.from, this.to, room.roomId, this.reservationId).subscribe(
      value => this.checkedIn.emit()
    )
  }
}
