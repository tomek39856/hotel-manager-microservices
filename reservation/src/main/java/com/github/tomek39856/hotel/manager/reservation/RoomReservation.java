package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.dto.RoomReservationDto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

class RoomReservation {
  private final String id = UUID.randomUUID().toString();
  private final ReservableRoom reservableRoom;
  private final LocalDate start;
  private final LocalDate end;
  private final Instant reservedAt = Instant.now();
  private ReservationStatus status = ReservationStatus.NEW;

  RoomReservation(ReservableRoom reservableRoom, LocalDate start, LocalDate end) {
    this.reservableRoom = reservableRoom;
    this.start = start;
    this.end = end;
  }

  String getId() {
    return id;
  }

  ReservationStatus getStatus() {
    return status;
  }

  LocalDate getStart() {
    return start;
  }

  LocalDate getEnd() {
    return end;
  }

  boolean isOverlapping(LocalDate from, LocalDate to) {
    return start.isBefore(to) && from.isBefore(end);
  }

  void cancel() {
    status = ReservationStatus.CANCELLED;
  }

  RoomReservationDto toDto() {
    return new RoomReservationDto(id, start, end, reservedAt, status, reservableRoom.getRoomType());
  }

  void confirmGuestArrival() {
    status = ReservationStatus.ACTIVE;
  }

  void confirmReservation() {
    status = ReservationStatus.CONFIRMED;
  }
}
