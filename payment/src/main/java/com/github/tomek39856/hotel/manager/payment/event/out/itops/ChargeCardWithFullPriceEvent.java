package com.github.tomek39856.hotel.manager.payment.event.out.itops;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.payment.dto.PaymentInformationDto;

public class ChargeCardWithFullPriceEvent extends ChargeCardEvent {
  @JsonCreator
  public ChargeCardWithFullPriceEvent(PaymentInformationDto payment) {
    super(payment, 100);
  }
}
