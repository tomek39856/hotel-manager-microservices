package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.event.in.CardChargedEvent;
import com.github.tomek39856.hotel.manager.payment.event.in.ChargeCardFailedEvent;
import com.github.tomek39856.hotel.manager.payment.event.in.HoldCreatedEvent;
import com.github.tomek39856.hotel.manager.payment.event.in.HoldFailedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
class CardChargingEventsListener {
  private final UpdatePaymentStatusUseCase updatePaymentStatusUseCase;

  CardChargingEventsListener(UpdatePaymentStatusUseCase updatePaymentStatusUseCase) {
    this.updatePaymentStatusUseCase = updatePaymentStatusUseCase;
  }

  @JmsListener(destination = "itops.card.charge", selector = "type = 'CardChargedEvent'")
  void handle(CardChargedEvent event) {
    updatePaymentStatusUseCase.chargeSuccess(event.getPaymentId());
  }

  @JmsListener(destination = "itops.card.charge", selector = "type = 'ChargeCardFailedEvent'")
  void handle(ChargeCardFailedEvent event) {
    updatePaymentStatusUseCase.chargeFailed(event.getPaymentId());
  }

  @JmsListener(destination = "itops.card.hold", selector = "type = 'HoldCreatedEvent'")
  void handle(HoldCreatedEvent event) {
    updatePaymentStatusUseCase.holdSuccess(event.getPaymentId());
  }

  @JmsListener(destination = "itops.card.hold", selector = "type = 'HoldFailedEvent'")
  void handle(HoldFailedEvent event) {
    updatePaymentStatusUseCase.holdFailed(event.getPaymentId());
  }
}
