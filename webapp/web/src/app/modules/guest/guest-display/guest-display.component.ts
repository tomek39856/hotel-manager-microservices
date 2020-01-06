import {Component, Input, OnInit} from '@angular/core';
import {Guest} from '../guest';

@Component({
  selector: 'app-guest-display',
  templateUrl: './guest-display.component.html',
  styleUrls: ['./guest-display.component.sass']
})
export class GuestDisplayComponent implements OnInit {
  @Input()
  guest: Guest;

  constructor() { }

  ngOnInit() {
  }

}
