import {ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {RateService} from "../../services/rate.service";

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
  rate: number = 0;

  constructor(private rateService: RateService, private changeDetector: ChangeDetectorRef) { }

  ngOnChanges(changes: SimpleChanges): void {
    this.rateService.getRate(this.room_type, this.from, this.to)
      .subscribe(value => {
        this.rate = value
        this.changeDetector.detectChanges()
      })
  }
}
