package com.github.tomek39856.hotel.manager.itops.event;


import com.github.tomek39856.hotel.manager.itops.dto.PaymentInformationDto;

public class ChargeCardWithFullPriceEvent extends ChargeCardEvent {
  public ChargeCardWithFullPriceEvent(PaymentInformationDto payment) {
    super(payment, 100);
  }
}
