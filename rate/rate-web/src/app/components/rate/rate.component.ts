import {
  ChangeDetectionStrategy,
  ChangeDetectorRef,
  Component,
  Input,
  OnChanges,
  SimpleChanges
} from '@angular/core';
import {BehaviorSubject, Subject} from 'rxjs';
import {RateService} from "../../services/rate.service";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-rate',
  templateUrl: './rate.component.html',
  styleUrls: ['./rate.component.sass'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class RateComponent implements OnChanges {
  @Input()
  room_type: string;
  @Input()
  from: string;
  @Input()
  to: string;
  rateNumber: number = 0;
  rateSubject: Subject<number> = new BehaviorSubject(undefined);

  constructor(private rateService: RateService, private ref: ChangeDetectorRef) { }

  getRate() {
    this.rateService.getRate(this.room_type, this.from, this.to).
      subscribe(x => this.rateSubject.next(x));
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.rateService.getRate(this.room_type, this.from, this.to)
      .pipe(tap(x => console.log(x)))
      .subscribe(value => {
        this.rateNumber = value
        this.ref.detectChanges()
      })
  }
}
