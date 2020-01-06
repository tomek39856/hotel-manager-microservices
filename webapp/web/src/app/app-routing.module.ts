import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {RoomSelectionComponent} from './pages/guest-pages/room-selection/room-selection.component';
import {ReservationDetailsComponent} from './pages/guest-pages/reservation-details/reservation-details.component';
import {PaymentDetailsComponent} from './pages/guest-pages/payment-details/payment-details.component';
import {ReservationSuccessComponent} from './pages/guest-pages/reservation-success/reservation-success.component';
import {FindGuestComponent} from './pages/reception-pages/find-guest/find-guest.component';
import {CheckInComponent} from './pages/reception-pages/check-in/check-in.component';


const routes: Routes = [
  {path: '', component: RoomSelectionComponent},
  {path: 'selection', component: RoomSelectionComponent},
  {path: 'reservation-details/:id', component: ReservationDetailsComponent },
  {path: 'payment-details/:id', component: PaymentDetailsComponent },
  {path: 'reservation-success/:id', component: ReservationSuccessComponent },
  {path: 'reception', children: [
    {path: 'find-guest', component: FindGuestComponent},
    {path: 'check-in/:id/reservation/:reservationId', component: CheckInComponent},
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
