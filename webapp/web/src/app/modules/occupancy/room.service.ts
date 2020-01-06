import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Room} from './room';

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  private ROOM_URL = 'http://localhost:8080/room';

  constructor(private httpClient: HttpClient) { }

  getFreeRooms(from: string, to: string, roomType: string): Observable<Room[]> {
    const params = new HttpParams()
      .append('roomType', roomType)
      .append('from', from)
      .append('to', to);
    return this.httpClient.get<Room[]>(this.ROOM_URL + '/not-occupied', {params: params});
  }

  checkIn(from: string, to: string, roomId: string, reservationId: string): Observable<void> {
    console.log({'from': from, 'to': to, 'roomId': roomId, 'reservationId': reservationId})
    return this.httpClient.post<void>(this.ROOM_URL + '/check-in', {'from': from, 'to': to, 'roomId': roomId, 'reservationId': reservationId})
  }
}
