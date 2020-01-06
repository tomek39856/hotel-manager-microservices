package com.github.tomek39856.hotel.manager.reservation.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.tomek39856.hotel.manager.reservation.ReservationStatus;
import com.github.tomek39856.hotel.manager.reservation.RoomType;

import java.time.Instant;
import java.time.LocalDate;

public class RoomReservationDto {
  private final String id;
  private final LocalDate start;
  private final LocalDate end;
  private final Instant reservedAt;
  private final ReservationStatus status;
  private final RoomType roomType;

  @JsonCreator
  public RoomReservationDto(String id, LocalDate start, LocalDate end, Instant reservedAt, ReservationStatus status, RoomType roomType) {
    this.id = id;
    this.start = start;
    this.end = end;
    this.reservedAt = reservedAt;
    this.status = status;
    this.roomType = roomType;
  }

  public String getId() {
    return id;
  }

  public LocalDate getStart() {
    return start;
  }

  public LocalDate getEnd() {
    return end;
  }

  public Instant getReservedAt() {
    return reservedAt;
  }

  public RoomType getRoomType() {
    return roomType;
  }
}
