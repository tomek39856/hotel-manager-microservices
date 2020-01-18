package com.github.tomek39856.hotel.manager.rate.provider.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public class RoomRate {
  private final BigDecimal sum;

  @JsonCreator
  public RoomRate(BigDecimal sum) {
    this.sum = sum;
  }

  public BigDecimal getSum() {
    return sum;
  }
}
