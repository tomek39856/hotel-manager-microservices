import { BrowserModule } from '@angular/platform-browser';
import {Injector, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {AvaliableSearchComponent} from './components/avaliable-search/avaliable-search.component';
import {ConfirmArrivalComponent} from "./components/confirm-arrival/confirm-arrival.component";
import {ReservationComponent} from "./components/reservation/reservation.component";
import {ReserveComponent} from "./components/reserve/reserve.component";
import {createCustomElement} from "@angular/elements";
import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    AvaliableSearchComponent,
    ConfirmArrivalComponent,
    ReservationComponent,
    ReserveComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  entryComponents: [
    AppComponent,
    AvaliableSearchComponent,
    ConfirmArrivalComponent,
    ReservationComponent,
    ReserveComponent
  ],
  exports: [
    AvaliableSearchComponent
  ],
  bootstrap: []
})
export class AppModule {
  constructor(private injector: Injector) {

  }

  ngDoBootstrap() {
    customElements.define('app-avaliable-search', createCustomElement(AvaliableSearchComponent, { injector: this.injector }));
    customElements.define('app-reserve', createCustomElement(ReserveComponent, { injector: this.injector }));
    customElements.define('app-reservation', createCustomElement(ReservationComponent, { injector: this.injector }));
    customElements.define('app-confirm-arrival', createCustomElement(ConfirmArrivalComponent, { injector: this.injector }));
  }
}
