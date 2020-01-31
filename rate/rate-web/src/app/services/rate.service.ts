import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {map, switchMap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RateService {
  private RATE_URL = 'http://localhost:8080/rate';


  constructor(private httpClient: HttpClient) {
  }

  getRate(roomType: string, from, to): Observable<number> {
    const params = new HttpParams()
      .append('roomType', roomType)
      .append('from', from)
      .append('to', to);
    return this.httpClient.get<{sum: number}>(this.RATE_URL, {'params': params}).pipe(
      map(value => value.sum)
    );
  }
}
