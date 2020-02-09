import { BrowserModule } from '@angular/platform-browser';
import {Injector, NgModule} from '@angular/core';

import {createCustomElement} from "@angular/elements";
import {HttpClientModule} from "@angular/common/http";
import {GuestDisplayComponent} from "./components/guest-display/guest-display.component";
import {GuestProvideComponent} from "./components/guest-provide/guest-provide.component";
import {GuestSearchComponent} from "./components/guest-search/guest-search.component";
import {ReservationGuestComponent} from "./components/reservation-guest/reservation-guest.component";
import {ReactiveFormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    GuestDisplayComponent,
    GuestProvideComponent,
    GuestSearchComponent,
    ReservationGuestComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  entryComponents: [
    GuestDisplayComponent,
    GuestProvideComponent,
    GuestSearchComponent,
    ReservationGuestComponent
  ],
  exports: [
    GuestDisplayComponent,
    GuestProvideComponent,
    GuestSearchComponent,
    ReservationGuestComponent
  ],
  bootstrap: []
})

export class AppModule {
  constructor(private injector: Injector) {
  }

  ngDoBootstrap() {
    customElements.define('app-guest-display', createCustomElement(GuestDisplayComponent, { injector: this.injector }));
    customElements.define('app-guest-provide', createCustomElement(GuestProvideComponent, { injector: this.injector }));
    customElements.define('app-guest-search', createCustomElement(GuestSearchComponent, { injector: this.injector }));
    customElements.define('app-reservation-guest', createCustomElement(ReservationGuestComponent, { injector: this.injector }));
  }
}
