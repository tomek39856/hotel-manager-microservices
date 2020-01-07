package com.github.tomek39856.hotel.manager.occupancy;

import java.time.LocalDate;

class Occupancy {
  private final Room room;
  private final String reservationId;
  private final LocalDate start;
  private final LocalDate end;

  Occupancy(Room room, String reservationId, LocalDate start, LocalDate end) {
    this.room = room;
    this.reservationId = reservationId;
    this.start = start;
    this.end = end;
  }

  boolean isOverlapping(LocalDate from, LocalDate to) {
    return start.isBefore(to) && from.isBefore(end);
  }

  String getReservationId() {
    return reservationId;
  }

  Room getRoom() {
    return room;
  }
}
