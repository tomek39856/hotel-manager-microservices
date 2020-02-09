import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Observable} from 'rxjs';
import {FormBuilder, FormGroup} from '@angular/forms';
import {GuestService} from "../../services/guest.service";

@Component({
  selector: 'app-guest-provide',
  templateUrl: './guest-provide.component.html',
  styleUrls: ['./guest-provide.component.sass']
})
export class GuestProvideComponent implements OnInit, OnChanges {
  @Input()
  submit_events: Observable<void>;
  @Input()
  reservation_id: string;
  @Output('create-success')
  createSuccess: EventEmitter<void> = new EventEmitter<void>();
  @Output('create-failed')
  createFailed: EventEmitter<void> = new EventEmitter<void>();
  form: FormGroup;

  constructor(private guestService: GuestService, private fb: FormBuilder) { }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    this.form = this.fb.group({
        name: this.fb.control('Jan'),
        lastName: this.fb.control('Kowalski')
      }
    );
  }

  private getFieldValue(fieldName: string) {
    console.log(this.form.get(fieldName).value)
    return this.form.get(fieldName).value;
  }

  private saveGuest() {
    this.guestService
      .create({'reservationId': this.reservation_id, 'firstName': this.getFieldValue('name'), 'lastName': this.getFieldValue('lastName')})
      .subscribe(val => {this.createSuccess.emit()}, error => {this.createFailed.emit()})
  }

  ngOnChanges(changes: SimpleChanges): void {

    this.submit_events.subscribe(
      value => this.saveGuest()
    )
  }
}
