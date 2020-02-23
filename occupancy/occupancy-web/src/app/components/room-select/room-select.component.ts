import {
  ChangeDetectionStrategy,
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges
} from '@angular/core';
import {Room} from "../../model/room";
import {RoomService} from "../../services/room.service";

@Component({
  selector: 'app-room-check-in',
  templateUrl: './room-select.component.html',
  styleUrls: ['./room-display.component.sass'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class RoomSelectComponent implements OnInit, OnChanges {
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

  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.rooms)
  }

  checkIn(room: Room) {
    this.roomService.checkIn(this.from, this.to, room.roomId, this.reservation_id).subscribe(
      value => this.checked_in.emit()
    )
  }
}
