import { BrowserModule } from '@angular/platform-browser';
import {Injector, NgModule} from '@angular/core';

import {RateComponent} from "./components/rate/rate.component";
import {createCustomElement} from "@angular/elements";
import {RateService} from "./services/rate.service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    RateComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [RateService],
  entryComponents: [
    RateComponent
  ],
  exports: [
    RateComponent
  ],
  bootstrap: []
})

export class AppModule {
  constructor(private injector: Injector) {
  }

  ngDoBootstrap() {
    customElements.define('app-rate', createCustomElement(RateComponent, { injector: this.injector }));
  }
}
