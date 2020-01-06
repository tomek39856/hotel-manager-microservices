import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PaymentData} from './payment-data';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private PAYMENT_URL = 'http://localhost:8080/payment';

  constructor(private httpClient: HttpClient) { }

  create(paymentData: PaymentData): Observable<string> {
    console.log(paymentData)
    return this.httpClient.post<string>(this.PAYMENT_URL, paymentData).pipe(
      map(value => value['id'])
    );
  }

  getStatus(reservationId: string): Observable<string> {
    return this.httpClient.get<string>(this.PAYMENT_URL + '/status?reservationId=' + reservationId);
  }
}
