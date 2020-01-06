import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AvaliableSearchComponent } from './modules/reservation/avaliable-search/avaliable-search.component';
import { ReserveComponent } from './modules/reservation/reserve/reserve.component';
import { RoomSelectionComponent } from './pages/guest-pages/room-selection/room-selection.component';
import { StayDetailsComponent } from './pages/guest-pages/stay-details/stay-details.component';
import { RateComponent } from './modules/rate/rate.component';
import {ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { DescriptionComponent } from './modules/marketing/description/description.component';
import { ReservationDetailsComponent } from './pages/guest-pages/reservation-details/reservation-details.component';
import { GuestProvideComponent } from './modules/guest/guest-provide/guest-provide.component';
import { PaymentDataComponent } from './modules/payment/payment-data/payment-data.component';
import { PaymentDetailsComponent } from './pages/guest-pages/payment-details/payment-details.component';
import { PaymentStatusComponent } from './modules/payment/payment-status/payment-status.component';
import { HoldStatusComponent } from './modules/payment/hold-status/hold-status.component';
import { ReservationSuccessComponent } from './pages/guest-pages/reservation-success/reservation-success.component';
import { ReservationComponent } from './modules/reservation/reservation/reservation.component';
import { ReservationGuestComponent } from './modules/guest/reservation-guest/reservation-guest.component';
import { FindGuestComponent } from './pages/reception-pages/find-guest/find-guest.component';
import { GuestSearchComponent } from './modules/guest/guest-search/guest-search.component';
import { ConfirmArrivalComponent } from './modules/reservation/confirm-arrival/confirm-arrival.component';
import { GuestDisplayComponent } from './modules/guest/guest-display/guest-display.component';
import { CheckInComponent } from './pages/reception-pages/check-in/check-in.component';
import { FreeRoomSearchComponent } from './modules/occupancy/free-room-search/free-room-search.component';
import { RoomCheckInComponent } from './modules/occupancy/room-check-in/room-check-in.component';
import { RoomPropertiesComponent } from './modules/occupancy/room-properties/room-properties.component';
import { CheckInDetailsComponent } from './pages/reception-pages/check-in-details/check-in-details.component';
import { ChargeStatusComponent } from './modules/payment/charge-status/charge-status.component';

@NgModule({
  declarations: [
    AppComponent,
    AvaliableSearchComponent,
    ReserveComponent,
    RoomSelectionComponent,
    StayDetailsComponent,
    RateComponent,
    DescriptionComponent,
    ReservationDetailsComponent,
    GuestProvideComponent,
    PaymentDataComponent,
    PaymentDetailsComponent,
    PaymentStatusComponent,
    HoldStatusComponent,
    ReservationSuccessComponent,
    ReservationComponent,
    ReservationGuestComponent,
    FindGuestComponent,
    GuestSearchComponent,
    ConfirmArrivalComponent,
    GuestDisplayComponent,
    CheckInComponent,
    FreeRoomSearchComponent,
    RoomCheckInComponent,
    RoomPropertiesComponent,
    CheckInDetailsComponent,
    ChargeStatusComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
