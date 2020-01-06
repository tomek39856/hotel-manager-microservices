import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';
import {Guest} from './guest';

@Injectable({
  providedIn: 'root'
})
export class GuestService {
  private GUEST_URL = 'http://localhost:8080/guest';

  constructor(private httpClient: HttpClient) { }

  create(guest): Observable<void> {
    return this.httpClient.post<void>(this.GUEST_URL, guest);
  }

  findForReservation(reservationId: string): Observable<Guest> {
    return this.httpClient.get<Guest>(this.GUEST_URL + '?reservationId=' + reservationId);
  }

  findByLastName(lastName: string): Observable<Guest[]> {
    return this.httpClient.get<Guest[]>(this.GUEST_URL + '?lastName=' + lastName);
  }
}
