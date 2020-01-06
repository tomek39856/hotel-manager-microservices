import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Reservation} from './reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private RESERVATION_URL = 'http://localhost:8080/reservation';

  constructor(private httpClient: HttpClient) { }

  reserve(roomType: string, from: string, to: string): Observable<string> {
    return this.httpClient.post<string[]>(this.RESERVATION_URL, {'roomType': roomType, 'from': from, 'to': to}).pipe(
      map(value => value['id'])
    )
  }

  getById(reservationId: string): Observable<Reservation> {
    return this.httpClient.get<Reservation>(this.RESERVATION_URL + '/' + reservationId);
  }

  confirmArrival(reservationId: string): Observable<void> {
    return this.httpClient.post<void>(this.RESERVATION_URL + '/' + reservationId + '/guest-arrival', null);
  }
}
