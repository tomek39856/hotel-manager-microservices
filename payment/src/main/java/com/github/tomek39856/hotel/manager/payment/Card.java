package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.dto.CardDto;
import com.github.tomek39856.hotel.manager.payment.dto.CreatePaymentDto;

import java.time.LocalDate;
import java.util.UUID;

class Card {
  private final String id = UUID.randomUUID().toString();
  private final String owner;
  private final String number;
  private final LocalDate validityDate;

  private Card(String owner, String number, LocalDate validityDate) {
    this.owner = owner;
    this.number = number;
    this.validityDate = validityDate;
  }

  static Card ofDto(CreatePaymentDto createPaymentDto) {
    return new Card(
        createPaymentDto.getCardOwner(),
        createPaymentDto.getCardNumber(),
        createPaymentDto.getCardValidity()
    );
  }

  CardDto toDto() {
    return new CardDto(
        owner,
        number,
        validityDate
    );
  }
}
