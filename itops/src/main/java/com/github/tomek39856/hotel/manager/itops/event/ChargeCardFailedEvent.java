package com.github.tomek39856.hotel.manager.itops.event;

import com.github.tomek39856.hotel.manager.itops.infrastructure.Event;

public class ChargeCardFailedEvent implements Event {
  private final String paymentId;

  public ChargeCardFailedEvent(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getPaymentId() {
    return paymentId;
  }
}
