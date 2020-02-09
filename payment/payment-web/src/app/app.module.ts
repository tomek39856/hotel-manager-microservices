import { BrowserModule } from '@angular/platform-browser';
import {Injector, NgModule} from '@angular/core';

import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {ChargeStatusComponent} from "./components/charge-status/charge-status.component";
import {HoldStatusComponent} from "./components/hold-status/hold-status.component";
import {PaymentDataComponent} from "./components/payment-data/payment-data.component";
import {PaymentStatusComponent} from "./components/payment-status/payment-status.component";
import {createCustomElement} from "@angular/elements";

@NgModule({
  declarations: [
    ChargeStatusComponent,
    HoldStatusComponent,
    PaymentDataComponent,
    PaymentStatusComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  entryComponents: [
    ChargeStatusComponent,
    HoldStatusComponent,
    PaymentDataComponent,
    PaymentStatusComponent
  ],
  exports: [
    PaymentDataComponent
  ],

  bootstrap: []
})
export class AppModule {
  constructor(private injector: Injector) {

  }

  ngDoBootstrap() {
    customElements.define('app-charge-status', createCustomElement(ChargeStatusComponent, { injector: this.injector }));
    customElements.define('app-hold-status', createCustomElement(HoldStatusComponent, { injector: this.injector }));
    customElements.define('app-payment-data', createCustomElement(PaymentDataComponent, { injector: this.injector }));
    customElements.define('app-payment-status', createCustomElement(PaymentStatusComponent, { injector: this.injector }));
  }
}
