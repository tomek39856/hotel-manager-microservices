package com.github.tomek39856.hotel.manager.reservation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.reservation.RoomType;

import java.time.LocalDate;

public class CreateRoomReservationDto {
  private final RoomType roomType;
  private final LocalDate from;
  private final LocalDate to;

  @JsonCreator
  public CreateRoomReservationDto(RoomType roomType, LocalDate from, LocalDate to) {
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
