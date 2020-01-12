package com.github.tomek39856.hotel.manager.itops.event.in;

import com.github.tomek39856.hotel.manager.itops.dto.PaymentInformationDto;
import com.github.tomek39856.hotel.manager.itops.infrastructure.Event;

public class ChargeCardEvent implements Event {

  private final PaymentInformationDto payment;
  private final long amountChargePercentage;

  public ChargeCardEvent(PaymentInformationDto payment, long amountChargePercentage) {
    this.payment = payment;
    this.amountChargePercentage = amountChargePercentage;
  }

  public PaymentInformationDto getPayment() {
    return payment;
  }

  public long getAmountChargePercentage() {
    return amountChargePercentage;
  }

}
