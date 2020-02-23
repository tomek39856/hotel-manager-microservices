import { BrowserModule } from '@angular/platform-browser';
import {Injector, NgModule} from '@angular/core';

import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";

import {createCustomElement} from "@angular/elements";
import {FreeRoomSearchComponent} from "./components/free-room-search/free-room-search.component";
import {RoomSelectComponent} from "./components/room-select/room-select.component";
import {RoomPropertiesComponent} from "./components/room-properties/room-properties.component";
import { CheckInToRoomComponent } from './components/check-in-to-room/check-in-to-room.component';

@NgModule({
  declarations: [
    FreeRoomSearchComponent,
    RoomSelectComponent,
    RoomPropertiesComponent,
    CheckInToRoomComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  entryComponents: [
    CheckInToRoomComponent
  ],
  exports: [
    CheckInToRoomComponent
  ],
  bootstrap: []
})
export class AppModule {
  constructor(private injector: Injector) {

  }

  ngDoBootstrap() {
    customElements.define('app-check-in-to-room', createCustomElement(CheckInToRoomComponent, { injector: this.injector }));
  }
}
