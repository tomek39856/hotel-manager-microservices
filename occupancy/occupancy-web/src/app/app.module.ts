import { BrowserModule } from '@angular/platform-browser';
import {Injector, NgModule} from '@angular/core';

import {HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";

import {createCustomElement} from "@angular/elements";
import {FreeRoomSearchComponent} from "./components/free-room-search/free-room-search.component";
import {RoomCheckInComponent} from "./components/room-check-in/room-check-in.component";
import {RoomPropertiesComponent} from "./components/room-properties/room-properties.component";

@NgModule({
  declarations: [
    FreeRoomSearchComponent,
    RoomCheckInComponent,
    RoomPropertiesComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  entryComponents: [
    FreeRoomSearchComponent,
    RoomCheckInComponent,
    RoomPropertiesComponent
  ],
  exports: [],
  bootstrap: []
})
export class AppModule {
  constructor(private injector: Injector) {

  }

  ngDoBootstrap() {
    customElements.define('app-free-room-search', createCustomElement(FreeRoomSearchComponent, { injector: this.injector }));
    customElements.define('app-room-check-in', createCustomElement(RoomCheckInComponent, { injector: this.injector }));
    customElements.define('app-room-properties', createCustomElement(RoomPropertiesComponent, { injector: this.injector }));
  }
}
