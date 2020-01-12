package com.github.tomek39856.hotel.manager.payment.event.out.itops;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.payment.dto.PaymentInformationDto;

public class ChargeCancellationFeeEvent extends ChargeCardEvent {
  @JsonCreator
  public ChargeCancellationFeeEvent(PaymentInformationDto payment) {
    super(payment, 10);
  }
}
