package com.github.tomek39856.hotel.manager.occupancy.event.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.occupancy.infrastructure.Event;

public class ReservationPaymentFailedEvent implements Event {
  private final String reservationId;

  @JsonCreator
  public ReservationPaymentFailedEvent(String reservationId) {
    this.reservationId = reservationId;
  }

  public String getReservationId() {
    return reservationId;
  }
}
