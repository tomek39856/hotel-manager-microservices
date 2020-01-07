package com.github.tomek39856.hotel.manager.occupancy.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.occupancy.RoomType;

import java.time.LocalDate;

public class SearchParametersDto {
  private final LocalDate from;
  private final LocalDate to;
  private final RoomType roomType;

  @JsonCreator
  public SearchParametersDto(LocalDate from, LocalDate to, RoomType roomType) {
    this.from = from;
    this.to = to;
    this.roomType = roomType;
  }

  public LocalDate getFrom() {
    return from;
  }

  public LocalDate getTo() {
    return to;
  }

  public RoomType getRoomType() {
    return roomType;
  }
}
