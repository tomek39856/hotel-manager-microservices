import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Room} from "../../model/room";
import {RoomService} from "../../services/room.service";

@Component({
  selector: 'app-free-room-search',
  templateUrl: './free-room-search.component.html',
  styleUrls: ['./free-room-search.component.sass']
})
export class FreeRoomSearchComponent implements OnInit {
  @Input()
  from: string;
  @Input()
  to: string;
  @Input()
  room_type: string;
  @Output()
  rooms_available: EventEmitter<Room[]> = new EventEmitter<Room[]>()

  constructor(private roomService: RoomService) { }

  ngOnInit() {
  }

  search() {
    this.roomService.getFreeRooms(this.from, this.to, this.room_type).subscribe(
      value => this.rooms_available.emit(value)
    );
  }
}
