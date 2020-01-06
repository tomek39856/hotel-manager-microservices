package com.github.tomek39856.hotel.manager.payment.event.in;

import com.github.tomek39856.hotel.manager.payment.infrastructure.Event;

public class ChargeCardFailedEvent implements Event {
  private final String paymentId;

  public ChargeCardFailedEvent(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getPaymentId() {
    return paymentId;
  }
}
