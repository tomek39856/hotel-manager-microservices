package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.event.ReservationHoldEstablishedEvent;
import com.github.tomek39856.hotel.manager.reservation.event.ReservationPaymentFailedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class ReservationPaymentEventListener {
  private final CancelReservationUseCase cancelReservationUseCase;
  private final ConfirmReservationUseCase confirmReservationUseCase;

  ReservationPaymentEventListener(CancelReservationUseCase cancelReservationUseCase, ConfirmReservationUseCase confirmReservationUseCase) {
    this.cancelReservationUseCase = cancelReservationUseCase;
    this.confirmReservationUseCase = confirmReservationUseCase;
  }

  @EventListener
  void handle(ReservationHoldEstablishedEvent event) {
    confirmReservationUseCase.execute(event.getReservationId());
  }

  @EventListener
  void handle(ReservationPaymentFailedEvent event) {
    cancelReservationUseCase.execute(event.getReservationId());
  }
}
