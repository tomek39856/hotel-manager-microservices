package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.dto.CreatePaymentDto;
import com.github.tomek39856.hotel.manager.payment.dto.PaymentInformationDto;
import com.github.tomek39856.hotel.manager.payment.event.out.ReservationHoldEstablishedEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.ReservationPaymentFailedEvent;
import com.github.tomek39856.hotel.manager.payment.infrastructure.EventPublisher;

import java.util.UUID;

class PaymentInformation {
  private final String id = UUID.randomUUID().toString();
  private final String reservationId;
  private final Card card;
  private PaymentStatus paymentStatus = PaymentStatus.NEW;

  private PaymentInformation(String reservationId, Card card) {
    this.reservationId = reservationId;
    this.card = card;
  }

  static PaymentInformation ofDto(CreatePaymentDto createPaymentDto) {
    return new PaymentInformation(
        createPaymentDto.getReservationId(),
        Card.ofDto(createPaymentDto)
    );
  }

  String getId() {
    return id;
  }

  String getReservationId() {
    return reservationId;
  }

  Card getCard() {
    return card;
  }

  PaymentInformationDto toDto() {
    return new PaymentInformationDto(
        id,
        reservationId,
        card.toDto()
    );
  }

  void chargeFailed(EventPublisher eventPublisher) {
    paymentStatus = PaymentStatus.FAILED;
    eventPublisher.publishEvent(new ReservationPaymentFailedEvent(reservationId));
  }

  void chargeSuccess() {
    paymentStatus = PaymentStatus.CHARGED;
  }


  void holdSuccess(EventPublisher eventPublisher) {
    paymentStatus = PaymentStatus.HOLD;
    eventPublisher.publishEvent(new ReservationHoldEstablishedEvent(reservationId));
  }

  void holdFailed(EventPublisher eventPublisher) {
    paymentStatus = PaymentStatus.FAILED;
    eventPublisher.publishEvent(new ReservationPaymentFailedEvent(reservationId));
  }

  PaymentStatus getPaymentStatus() {
    return paymentStatus;
  }
}
