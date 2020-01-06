import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {RateService} from './rate.service';
import {BehaviorSubject, Subject} from 'rxjs';

@Component({
  selector: 'app-rate',
  templateUrl: './rate.component.html',
  styleUrls: ['./rate.component.sass']

})
export class RateComponent implements OnInit, OnChanges {
  @Input()
  roomType: string;
  @Input()
  from: string;
  @Input()
  to: string;
  rateSubject: Subject<number> = new BehaviorSubject(0);

  constructor(private rateService: RateService) { }

  ngOnInit() {
    this.getRate();
  }

  getRate() {
    this.rateService.getRate(this.roomType, this.from, this.to).
      subscribe(x => this.rateSubject.next(x));
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.getRate();
  }
}
