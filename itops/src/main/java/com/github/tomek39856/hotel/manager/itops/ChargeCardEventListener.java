package com.github.tomek39856.hotel.manager.itops;

import com.github.tomek39856.hotel.manager.itops.event.in.ChargeCardEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
class ChargeCardEventListener {
  private final ChargeCardUseCase chargeCardUseCase;

  ChargeCardEventListener(ChargeCardUseCase chargeCardUseCase) {
    this.chargeCardUseCase = chargeCardUseCase;
  }


  @JmsListener(destination = "payment.card.charge")
  void handle(ChargeCardEvent chargeCardEvent) {
    chargeCardUseCase.charge(chargeCardEvent.getPayment(), chargeCardEvent.getAmountChargePercentage());
  }

}
