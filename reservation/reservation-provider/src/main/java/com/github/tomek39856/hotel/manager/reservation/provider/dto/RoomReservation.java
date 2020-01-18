package com.github.tomek39856.hotel.manager.reservation.provider.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.Instant;
import java.time.LocalDate;

public class RoomReservation {
  private final String id;
  private final LocalDate start;
  private final LocalDate end;
  private final Instant reservedAt;
  private final String roomType;

  @JsonCreator
  public RoomReservation(String id, LocalDate start, LocalDate end, Instant reservedAt, String roomType) {
    this.id = id;
    this.start = start;
    this.end = end;
    this.reservedAt = reservedAt;
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

  public String getRoomType() {
    return roomType;
  }
}
