import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Observable} from 'rxjs';
import {GuestService} from '../guest.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import {PaymentData} from '../../payment/payment-data';
import {Guest} from '../guest';

@Component({
  selector: 'app-guest-details',
  templateUrl: './guest-provide.component.html',
  styleUrls: ['./guest-provide.component.sass']
})
export class GuestProvideComponent implements OnInit {
  @Input()
  submitEvents: Observable<void>;
  @Input()
  reservationId: string;
  @Output()
  createSuccess: EventEmitter<void> = new EventEmitter<void>();
  @Output()
  createFailed: EventEmitter<void> = new EventEmitter<void>();
  form: FormGroup;

  constructor(private guestService: GuestService, private fb: FormBuilder) { }

  ngOnInit() {
    this.initForm();
    this.submitEvents.subscribe(
      value => this.saveGuest()
    )
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
      .create({'reservationId': this.reservationId, 'firstName': this.getFieldValue('name'), 'lastName': this.getFieldValue('lastName')})
      .subscribe(val => {this.createSuccess.emit()}, error => {this.createFailed.emit()})
  }
}
