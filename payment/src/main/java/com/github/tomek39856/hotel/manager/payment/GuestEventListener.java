package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.event.in.NoShowEvent;
import com.github.tomek39856.hotel.manager.payment.event.in.UserCheckedInEvent;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@EnableJms
class GuestEventListener {
  private final ProceedPaymentForReservationUseCase proceedPaymentForReservationUseCase;
  private final ChargeCancellationFeeForReservationUseCase chargeCancellationFeeForReservationUseCase;

  GuestEventListener(ProceedPaymentForReservationUseCase proceedPaymentForReservationUseCase, ChargeCancellationFeeForReservationUseCase chargeCancellationFeeForReservationUseCase) {
    this.proceedPaymentForReservationUseCase = proceedPaymentForReservationUseCase;
    this.chargeCancellationFeeForReservationUseCase = chargeCancellationFeeForReservationUseCase;
  }

  @JmsListener(destination = "occupancy.checkIn")
  void handle(UserCheckedInEvent event) {
    proceedPaymentForReservationUseCase.execute(event.getReservationId());
  }

  @JmsListener(destination = "reservation.noShow")
  void handle(NoShowEvent event) {
    chargeCancellationFeeForReservationUseCase.execute(event.getReservationId());
  }
}
