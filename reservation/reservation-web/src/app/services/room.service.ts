import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RoomService {
  private ROOM_URL = 'http://localhost:8080/reservation/available-room';

  constructor(private httpClient: HttpClient) { }

  getRooms(from, to): Observable<string[]> {
    const params = new HttpParams()
      .append('from', from)
      .append('to', to);
    return this.httpClient.get<string[]>(this.ROOM_URL, {'params': params});
  }
}
