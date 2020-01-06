import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {RoomService} from '../room.service';
import {RoomsAvailableEvent} from './rooms-available-event';

@Component({
  selector: 'app-avaliable-search',
  templateUrl: './avaliable-search.component.html',
  styleUrls: ['./avaliable-search.component.sass']
})
export class AvaliableSearchComponent implements OnInit {
  @Output()
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
    const from = this.form.get('from').value;
    const to = this.form.get('to').value;
    this.roomService.getRooms(from, to).subscribe(
      value => this.roomsAvailable.emit(new RoomsAvailableEvent(value, from, to))
    )
  }

}
