package com.github.tomek39856.hotel.manager.reservation;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

class ReservableRoom {
  private final String id = UUID.randomUUID().toString();
  private final RoomType roomType;
  private final Set<RoomReservation> roomReservations = new HashSet<>();

  ReservableRoom(RoomType roomType) {
    this.roomType = roomType;
  }

  String getId() {
    return id;
  }

  boolean isAvailable(LocalDate from, LocalDate to) {
    return !roomReservations.stream()
        .filter(roomReservation -> ReservationStatus.CANCELLED != roomReservation.getStatus())
        .filter(roomReservation -> roomReservation.isOverlapping(from, to))
        .findAny()
        .isPresent();
  }

  Set<RoomReservation> getRoomReservations() {
    return roomReservations;
  }

  RoomType getRoomType() {
    return roomType;
  }

  RoomReservation reserve(LocalDate from, LocalDate to) {
    RoomReservation reservation = new RoomReservation(this, from, to);
    this.roomReservations.add(reservation);
    return reservation;
  }
}
