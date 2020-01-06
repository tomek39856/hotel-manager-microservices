package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.event.in.CardChargedEvent;
import com.github.tomek39856.hotel.manager.payment.event.in.ChargeCardFailedEvent;
import com.github.tomek39856.hotel.manager.payment.event.in.HoldCreatedEvent;
import com.github.tomek39856.hotel.manager.payment.event.in.HoldFailedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class CardChargingEventsListener {
  private final UpdatePaymentStatusUseCase updatePaymentStatusUseCase;

  CardChargingEventsListener(UpdatePaymentStatusUseCase updatePaymentStatusUseCase) {
    this.updatePaymentStatusUseCase = updatePaymentStatusUseCase;
  }

  @EventListener
  void handle(CardChargedEvent event) {
    updatePaymentStatusUseCase.chargeSuccess(event.getPaymentId());
  }

  @EventListener
  void handle(ChargeCardFailedEvent event) {
    updatePaymentStatusUseCase.chargeFailed(event.getPaymentId());
  }

  @EventListener
  void handle(HoldCreatedEvent event) {
    updatePaymentStatusUseCase.holdSuccess(event.getPaymentId());
  }

  @EventListener
  void handle(HoldFailedEvent event) {
    updatePaymentStatusUseCase.holdFailed(event.getPaymentId());
  }
}
