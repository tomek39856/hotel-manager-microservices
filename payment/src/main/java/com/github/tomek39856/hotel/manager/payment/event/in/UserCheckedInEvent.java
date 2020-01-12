package com.github.tomek39856.hotel.manager.payment.event.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.payment.infrastructure.Event;

public class UserCheckedInEvent implements Event {
  private final String reservationId;

  @JsonCreator
  public UserCheckedInEvent(String reservationId) {
    this.reservationId = reservationId;
  }

  public String getReservationId() {
    return reservationId;
  }
}
