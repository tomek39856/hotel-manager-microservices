package com.github.tomek39856.hotel.manager.reservation;

import java.time.LocalDate;

public class RoomFixture {
  public static ReservableRoom reservedRoom(LocalDate from, LocalDate to) {
    ReservableRoom reservedRoom = new ReservableRoom(RoomType.KING);
    reservedRoom.reserve(from, to);
    return reservedRoom;
  }

  public static ReservableRoom roomWithCanceledReservation(LocalDate from, LocalDate to) {
    ReservableRoom reservedRoom = new ReservableRoom(RoomType.KING);
    reservedRoom.reserve(from, to);
    reservedRoom.getRoomReservations().stream().findFirst().orElseThrow(RuntimeException::new).cancel();
    return reservedRoom;
  }
}
