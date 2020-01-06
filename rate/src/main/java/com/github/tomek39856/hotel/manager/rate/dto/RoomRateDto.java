package com.github.tomek39856.hotel.manager.rate.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public class RoomRateDto {
  private final BigDecimal sum;

  @JsonCreator
  public RoomRateDto(BigDecimal sum) {
    this.sum = sum;
  }

  public BigDecimal getSum() {
    return sum;
  }
}
