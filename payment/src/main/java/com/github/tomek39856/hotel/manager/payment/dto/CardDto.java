package com.github.tomek39856.hotel.manager.payment.dto;

import java.time.LocalDate;

public class CardDto {
  private final String owner;
  private final String number;
  private final LocalDate validityDate;

  public CardDto(String owner, String number, LocalDate validityDate) {
    this.owner = owner;
    this.number = number;
    this.validityDate = validityDate;
  }

  public String getOwner() {
    return owner;
  }

  public String getNumber() {
    return number;
  }

  public LocalDate getValidityDate() {
    return validityDate;
  }
}
