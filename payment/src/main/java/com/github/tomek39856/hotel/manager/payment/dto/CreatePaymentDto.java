package com.github.tomek39856.hotel.manager.payment.dto;

import java.time.LocalDate;

public class CreatePaymentDto {
  private final String reservationId;
  private final String cardOwner;
  private final String cardNumber;
  private final LocalDate cardValidity;

  public CreatePaymentDto(String reservationId, String cardOwner, String cardNumber, LocalDate cardValidity) {
    this.reservationId = reservationId;
    this.cardOwner = cardOwner;
    this.cardNumber = cardNumber;
    this.cardValidity = cardValidity;
  }

  public String getReservationId() {
    return reservationId;
  }

  public String getCardOwner() {
    return cardOwner;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public LocalDate getCardValidity() {
    return cardValidity;
  }
}
