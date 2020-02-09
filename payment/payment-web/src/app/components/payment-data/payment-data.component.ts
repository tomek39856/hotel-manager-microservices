import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Observable} from 'rxjs';
import {FormBuilder, FormGroup} from '@angular/forms';
import {PaymentService} from "../../services/payment.service";
import {PaymentData} from "../../model/payment-data";

@Component({
  selector: 'app-payment-data',
  templateUrl: './payment-data.component.html',
  styleUrls: ['./payment-data.component.sass']
})
export class PaymentDataComponent implements OnInit, OnChanges {
  @Input()
  submit_event: Observable<void>;
  @Input()
  reservation_id: string;
  @Output('payment-data-save-success')
  paymentDataSaveSuccess: EventEmitter<string> = new EventEmitter<string>();
  @Output('payment-data-save-failed')
  paymentDataSaveFailed: EventEmitter<void> = new EventEmitter<void>();

  form: FormGroup;

  constructor(private paymentService: PaymentService, private fb: FormBuilder) { }

  ngOnInit() {
    this.initForm();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.submit_event.subscribe(value => this.savePayment());
  }

  private savePayment() {
    this.paymentService
      .create(new PaymentData(this.reservation_id, this.getFieldValue('owner'), this.getFieldValue('cardNumber'), this.getFieldValue('cardValidity')))
      .subscribe(val => {console.log('emit payment success'); this.paymentDataSaveSuccess.emit(val)}, error => {this.paymentDataSaveFailed.emit()})
  }

  private initForm() {
    this.form = this.fb.group({
        owner: this.fb.control('Card Owner'),
        cardValidity: this.fb.control('2022-01-01'),
        cardNumber: this.fb.control('653214')
      }
    );
  }

  private getFieldValue(fieldName: string) {
    return this.form.get(fieldName).value;
  }

}
