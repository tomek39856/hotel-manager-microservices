import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Room} from '../room';

@Component({
  selector: 'app-room-properties',
  templateUrl: './room-properties.component.html',
  styleUrls: ['./room-properties.component.sass']
})
export class RoomPropertiesComponent implements OnInit {
  @Input()
  room: Room;
  @Output()
  roomForCheckInSelected: EventEmitter<Room> = new EventEmitter<Room>()

  constructor() { }

  ngOnInit() {
  }

  checkIn() {
    this.roomForCheckInSelected.emit(this.room);
  }

}
