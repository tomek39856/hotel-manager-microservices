import { BrowserModule } from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RoomSelectionComponent } from './pages/guest-pages/room-selection/room-selection.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { ReservationDetailsComponent } from './pages/guest-pages/reservation-details/reservation-details.component';
import { PaymentDetailsComponent } from './pages/guest-pages/payment-details/payment-details.component';
import { ReservationSuccessComponent } from './pages/guest-pages/reservation-success/reservation-success.component';
import { FindGuestComponent } from './pages/reception-pages/find-guest/find-guest.component';
import { CheckInComponent } from './pages/reception-pages/check-in/check-in.component';
import { CheckInDetailsComponent } from './pages/reception-pages/check-in-details/check-in-details.component';

@NgModule({
  declarations: [
    AppComponent,
    RoomSelectionComponent,
    ReservationDetailsComponent,
    PaymentDetailsComponent,
    ReservationSuccessComponent,
    FindGuestComponent,
    CheckInComponent,
    CheckInDetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule { }
