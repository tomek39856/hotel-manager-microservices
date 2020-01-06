package com.github.tomek39856.hotel.manager.payment.event.in;

import com.github.tomek39856.hotel.manager.payment.infrastructure.Event;

public class NoShowEvent implements Event {
  private final String reservationId;

  public NoShowEvent(String reservationId) {
    this.reservationId = reservationId;
  }

  public String getReservationId() {
    return reservationId;
  }
}
