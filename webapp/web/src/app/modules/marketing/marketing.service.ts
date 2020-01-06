import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Description} from './description';

@Injectable({
  providedIn: 'root'
})
export class MarketingService {
  private DESCRIPTION_URL = 'http://localhost:8080/marketing/description';

  constructor(private httpClient: HttpClient) { }

  getDescription(roomType: string): Observable<Description> {
    const params = new HttpParams()
      .append('roomType', roomType)
    return this.httpClient.get<Description>(this.DESCRIPTION_URL, {'params': params});
  }
}
