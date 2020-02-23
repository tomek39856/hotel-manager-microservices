import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Guest} from "../../model/guest";
import {GuestService} from "../../services/guest.service";

@Component({
  selector: 'app-guest-search',
  templateUrl: './guest-search.component.html',
  styleUrls: ['./guest-search.component.sass']
})
export class GuestSearchComponent implements OnInit {
  @Output()
  guests_found: EventEmitter<Guest[]> = new EventEmitter<Guest[]>();
  form: FormGroup;

  constructor(private guestService: GuestService, private fb: FormBuilder) { }

  ngOnInit() {
    this.form = this.fb.group({
        lastName: this.fb.control('Kowalski'),
      }
    );
  }

  search() {
    this.guestService.findByLastName(this.form.get('lastName').value).subscribe(
      value => this.guests_found.emit(value)
    );
  }

}
