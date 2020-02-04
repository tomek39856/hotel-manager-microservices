import {ChangeDetectorRef, Component, Input, OnChanges} from '@angular/core';
import {map} from 'rxjs/operators';
import {MarketingService} from "../../services/marketing.service";

@Component({
  selector: 'app-description',
  templateUrl: './description.component.html',
  styleUrls: ['./description.component.sass']
})
export class DescriptionComponent implements OnChanges {
  @Input('room-type')
  roomType: string;
  description: string = '';

  constructor(private marketingService: MarketingService, private changeDetector: ChangeDetectorRef) { }

  ngOnChanges() {
    this.marketingService.getDescription(this.roomType).pipe(
      map(value => value.description),
    ).subscribe(
      value => {
        this.description = value;
        this.changeDetector.detectChanges();
      }
    );
  }
}
