package com.github.tomek39856.hotel.manager.reservation.event.out;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.reservation.infrastructure.Event;

public class NoShowEvent implements Event {
  private final String reservationId;

  @JsonCreator
  public NoShowEvent(String reservationId) {
    this.reservationId = reservationId;
  }

  public String getReservationId() {
    return reservationId;
  }
}
