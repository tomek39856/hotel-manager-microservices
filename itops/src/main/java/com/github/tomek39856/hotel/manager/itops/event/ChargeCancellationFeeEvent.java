package com.github.tomek39856.hotel.manager.itops.event;


import com.github.tomek39856.hotel.manager.itops.dto.PaymentInformationDto;

public class ChargeCancellationFeeEvent extends ChargeCardEvent {
  public ChargeCancellationFeeEvent(PaymentInformationDto payment) {
    super(payment, 10);
  }
}
