package com.github.tomek39856.hotel.manager.itops.event.out;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.itops.infrastructure.Event;

public class HoldCreatedEvent implements Event {
  private final String paymentId;

  @JsonCreator
  public HoldCreatedEvent(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getPaymentId() {
    return paymentId;
  }
}
