package com.github.tomek39856.hotel.manager.occupancy.dto;

import java.time.LocalDate;

public class CheckInDto {
  private final String reservationId;
  private final String roomId;
  private final LocalDate from;
  private final LocalDate to;

  public CheckInDto(String reservationId, String roomId, LocalDate from, LocalDate to) {
    this.reservationId = reservationId;
    this.roomId = roomId;
    this.from = from;
    this.to = to;
  }

  public String getReservationId() {
    return reservationId;
  }

  public String getRoomId() {
    return roomId;
  }

  public LocalDate getFrom() {
    return from;
  }

  public LocalDate getTo() {
    return to;
  }
}
