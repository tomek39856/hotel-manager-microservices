import {Component, Input, OnInit} from '@angular/core';
import {MarketingService} from '../marketing.service';
import {Observable} from 'rxjs';
import {map, share} from 'rxjs/operators';

@Component({
  selector: 'app-description',
  templateUrl: './description.component.html',
  styleUrls: ['./description.component.sass']
})
export class DescriptionComponent implements OnInit {
  @Input()
  roomType: string;
  description$: Observable<string>;

  constructor(private marketingService: MarketingService) { }

  ngOnInit() {
    this.description$ = this.marketingService.getDescription(this.roomType).pipe(
      map(value => value.description),
      share()
    );
  }
}
