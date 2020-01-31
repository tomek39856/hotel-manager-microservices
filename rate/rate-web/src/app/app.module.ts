import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {RateComponent} from "./components/rate/rate.component";

@NgModule({
  declarations: [
    AppComponent,
    RateComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  entryComponents: [
    RateComponent
  ],
  bootstrap: []
})
export class AppModule { }
