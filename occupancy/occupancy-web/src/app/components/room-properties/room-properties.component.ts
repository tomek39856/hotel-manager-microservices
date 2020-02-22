import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Room} from "../../model/room";

@Component({
  selector: 'app-room-properties',
  templateUrl: './room-properties.component.html',
  styleUrls: ['./room-properties.component.sass']
})
export class RoomPropertiesComponent implements OnInit {
  @Input()
  room: Room;
  @Output()
  room_for_check_in_selected: EventEmitter<Room> = new EventEmitter<Room>()

  constructor() { }

  ngOnInit() {
  }

  checkIn() {
    this.room_for_check_in_selected.emit(this.room);
  }

}
