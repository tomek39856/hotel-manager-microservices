package com.github.tomek39856.hotel.manager.itops.event.in;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.itops.dto.PaymentInformationDto;
import com.github.tomek39856.hotel.manager.itops.infrastructure.Event;

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
