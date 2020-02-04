import { BrowserModule } from '@angular/platform-browser';
import {Injector, NgModule} from '@angular/core';

import {createCustomElement} from "@angular/elements";
import {HttpClientModule} from "@angular/common/http";
import {DescriptionComponent} from "./components/description/description.component";

@NgModule({
  declarations: [
    DescriptionComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [],
  entryComponents: [
    DescriptionComponent
  ],
  exports: [
    DescriptionComponent
  ],
  bootstrap: []
})

export class AppModule {
  constructor(private injector: Injector) {
  }

  ngDoBootstrap() {
    customElements.define('app-description', createCustomElement(DescriptionComponent, { injector: this.injector }));
  }
}
