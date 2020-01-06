import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {RoomService} from '../room.service';
import {Room} from '../room';

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
  roomType: string;
  @Output()
  roomsAvailable: EventEmitter<Room[]> = new EventEmitter<Room[]>()

  constructor(private roomService: RoomService) { }

  ngOnInit() {
  }

  search() {
    this.roomService.getFreeRooms(this.from, this.to, this.roomType).subscribe(
      value => this.roomsAvailable.emit(value)
    );
  }
}
