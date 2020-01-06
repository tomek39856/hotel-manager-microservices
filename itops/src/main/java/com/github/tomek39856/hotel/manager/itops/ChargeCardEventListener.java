package com.github.tomek39856.hotel.manager.itops;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
class ChargeCardEventListener {
  private final ChargeCardUseCase chargeCardUseCase;

  ChargeCardEventListener(ChargeCardUseCase chargeCardUseCase) {
    this.chargeCardUseCase = chargeCardUseCase;
  }

  /*
  @Async
  @EventListener
  void handle(ChargeCardEvent chargeCardEvent) {
    chargeCardUseCase.charge(chargeCardEvent.getPayment(), chargeCardEvent.getAmountChargePercentage());
  }
  */
}
