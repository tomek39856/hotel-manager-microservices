package com.github.tomek39856.hotel.manager.payment.dto;

public class PaymentInformationDto {
  private final String id;
  private final String reservationId;
  private final CardDto card;

  public PaymentInformationDto(String id, String reservationId, CardDto card) {
    this.id = id;
    this.reservationId = reservationId;
    this.card = card;
  }

  public String getReservationId() {
    return reservationId;
  }

  public CardDto getCard() {
    return card;
  }

  public String getId() {
    return id;
  }
}
