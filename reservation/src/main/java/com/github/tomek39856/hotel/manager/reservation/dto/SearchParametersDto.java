package com.github.tomek39856.hotel.manager.reservation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;

public class SearchParametersDto {
  private final LocalDate from;
  private final LocalDate to;

  @JsonCreator
  public SearchParametersDto(LocalDate from, LocalDate to) {
    this.from = from;
    this.to = to;
  }

  public LocalDate getFrom() {
    return from;
  }

  public LocalDate getTo() {
    return to;
  }
}
