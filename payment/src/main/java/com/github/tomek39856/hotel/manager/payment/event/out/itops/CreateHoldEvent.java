package com.github.tomek39856.hotel.manager.payment.event.out.itops;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.payment.dto.PaymentInformationDto;
import com.github.tomek39856.hotel.manager.payment.infrastructure.Event;

public class CreateHoldEvent implements Event {
  private final PaymentInformationDto payment;

  @JsonCreator
  public CreateHoldEvent(PaymentInformationDto payment) {
    this.payment = payment;
  }

  public PaymentInformationDto getPayment() {
    return payment;
  }
}
