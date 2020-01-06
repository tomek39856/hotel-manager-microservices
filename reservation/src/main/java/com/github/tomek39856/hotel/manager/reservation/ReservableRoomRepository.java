package com.github.tomek39856.hotel.manager.reservation;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
class ReservableRoomRepository {
  private final Map<String, ReservableRoom> rooms = new ConcurrentHashMap<>();

  Optional<ReservableRoom> findOneById(String id) {
    return Optional.ofNullable(rooms.get(id));
  }

  Set<RoomType> findAvailableRoomTypes(LocalDate from, LocalDate to) {
    return rooms.values().stream()
        .filter(room -> room.isAvailable(from, to))
        .map(ReservableRoom::getRoomType)
        .collect(Collectors.toCollection(() -> EnumSet.noneOf(RoomType.class)));
  }

  Set<String> findReservationIdByStatusConfirmedAndStartBefore(LocalDate latestStartDate) {
    return this.rooms.values().stream()
        .flatMap(room -> room.getRoomReservations().stream())
        .filter(reservation -> ReservationStatus.CONFIRMED == reservation.getStatus())
        .filter(reservation -> reservation.getStart().isBefore(latestStartDate))
        .map(RoomReservation::getId)
        .collect(Collectors.toSet());
  }

  Optional<ReservableRoom> findAvailableRoomByTypeAndAvailableBetween(RoomType roomType, LocalDate from, LocalDate to) {
    return rooms.values().stream()
        .filter(room -> room.getRoomType() == roomType)
        .filter(room -> room.isAvailable(from, to))
        .findFirst();
  }

  Optional<RoomReservation> findRoomReservationByReservationId(String reservationId) {
    return this.rooms.values().stream()
        .flatMap(room -> room.getRoomReservations().stream())
        .filter(reservation -> reservationId.equals(reservation.getId()))
        .findFirst();
  }

  void save(ReservableRoom reservableRoom) {
    this.rooms.put(reservableRoom.getId(), reservableRoom);
  }

  void clear() {
    this.rooms.clear();
  }
}
