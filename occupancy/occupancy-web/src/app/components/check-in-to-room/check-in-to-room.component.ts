import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Room} from "../../model/room";

@Component({
  selector: 'app-check-in-to-room',
  templateUrl: './check-in-to-room.component.html',
  styleUrls: ['./check-in-to-room.component.css']
})
export class CheckInToRoomComponent implements OnInit {
  rooms: Room[] = [];
  @Input()
  reservation_id: string;
  @Input()
  room_type: string;
  @Input()
  end: string;
  @Input()
  start: string;
  @Output()
  checked_in: EventEmitter<void> = new EventEmitter<void>();

  constructor() { }

  ngOnInit() {
  }

  roomsFound(rooms: Room[]) {
    this.rooms = rooms;
    console.log(this.rooms)
  }

  checkedIn() {
    this.checked_in.emit();
  }
}
