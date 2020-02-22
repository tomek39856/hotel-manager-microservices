import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Room} from "../../model/room";
import {RoomService} from "../../services/room.service";

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
  reservation_id: string;
  @Output()
  checked_in: EventEmitter<void> = new EventEmitter<void>();

  constructor(private roomService: RoomService) { }

  ngOnInit() {
  }

  checkIn(room: Room) {
    console.log(room);
    this.roomService.checkIn(this.from, this.to, room.roomId, this.reservation_id).subscribe(
      value => this.checked_in.emit()
    )
  }
}
