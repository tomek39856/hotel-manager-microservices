package com.github.tomek39856.hotel.manager.payment.event.in;

import com.github.tomek39856.hotel.manager.payment.infrastructure.Event;

public class HoldFailedEvent implements Event {
  private final String paymentId;

  public HoldFailedEvent(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getPaymentId() {
    return paymentId;
  }
}
