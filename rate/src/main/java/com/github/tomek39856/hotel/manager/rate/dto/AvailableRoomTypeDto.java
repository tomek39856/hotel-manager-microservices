package com.github.tomek39856.hotel.manager.rate.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.rate.RoomType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class AvailableRoomTypeDto {
  private final RoomType roomType;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private final LocalDate from;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private final LocalDate to;

  @JsonCreator
  public AvailableRoomTypeDto(RoomType roomType, LocalDate from, LocalDate to) {
    this.roomType = roomType;
    this.from = from;
    this.to = to;
  }

  public RoomType getRoomType() {
    return roomType;
  }

  public LocalDate getFrom() {
    return from;
  }

  public LocalDate getTo() {
    return to;
  }
}
