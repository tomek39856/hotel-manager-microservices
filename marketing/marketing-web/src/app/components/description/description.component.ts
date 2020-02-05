import {ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {map} from 'rxjs/operators';
import {MarketingService} from "../../services/marketing.service";

@Component({
  selector: 'app-description',
  templateUrl: './description.component.html',
  styleUrls: ['./description.component.sass'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DescriptionComponent implements OnChanges {
  @Input()
  room_type: string;
  description: string = '';

  constructor(private marketingService: MarketingService, private changeDetector: ChangeDetectorRef) { }

  ngOnChanges(changes: SimpleChanges): void {
    this.marketingService.getDescription(this.room_type).pipe(
      map(value => value.description),
    ).subscribe(
      value => {
        this.description = value;
        this.changeDetector.detectChanges();
      }
    );
  }
}
