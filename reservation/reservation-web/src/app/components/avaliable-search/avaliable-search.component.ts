import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {RoomsAvailableEvent} from './rooms-available-event';
import {RoomService} from "../../services/room.service";

@Component({
  selector: 'app-avaliable-search',
  templateUrl: './avaliable-search.component.html',
  styleUrls: ['./avaliable-search.component.sass']
})
export class AvaliableSearchComponent implements OnInit {
  @Output('rooms-available')
  roomsAvailable: EventEmitter<RoomsAvailableEvent> = new EventEmitter<RoomsAvailableEvent>();
  form: FormGroup;

  constructor(private fb: FormBuilder, private roomService: RoomService) { }

  ngOnInit() {
    this.form = this.fb.group({
        from: this.fb.control('2020-09-09'),
        to: this.fb.control('2020-09-15')
      }
    )
  }

  search() {
    console.log('search started')
    const from = this.form.get('from').value;
    const to = this.form.get('to').value;
    this.roomService.getRooms(from, to).subscribe(
      value => {
        console.log('rooms av')
        this.roomsAvailable.emit(new RoomsAvailableEvent(value, from, to))
      }
    )
  }

}
