package com.github.tomek39856.hotel.manager.occupancy;

import com.github.tomek39856.hotel.manager.occupancy.dto.RoomDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class Room {
  private final String id = UUID.randomUUID().toString();
  private final RoomType roomType;
  private final boolean parkView;
  private final boolean shower;
  private final boolean bath;
  private final List<Occupancy> occupancies = new ArrayList<>();

  Room(RoomType roomType, boolean parkView, boolean shower, boolean bath) {
    this.roomType = roomType;
    this.parkView = parkView;
    this.shower = shower;
    this.bath = bath;
  }

   RoomType getRoomType() {
    return roomType;
  }

   String getId() {
    return id;
  }

   List<Occupancy> getOccupancies() {
    return occupancies;
  }

  boolean isAvailable(LocalDate from, LocalDate to) {
    return !occupancies.stream()
        .filter(occupancy -> occupancy.isOverlapping(from, to))
        .findAny()
        .isPresent();
  }

   RoomDto toDto() {
    return new RoomDto(
      id,
      roomType,
      parkView,
      shower,
      bath
    );
  }

   void checkIn(String reservationId, LocalDate from, LocalDate to) {
    if(!isAvailable(from, to)) {
      throw new ConflictException();
    }
    Occupancy occupancy = new Occupancy(this, reservationId, from, to);
    this.occupancies.add(occupancy);
  }

   void cancelCheckIn(String reservationId) {
    Occupancy occupancy = occupancies.stream()
        .filter(o -> o.getReservationId().equals(reservationId))
        .findAny()
        .orElseThrow(NotFoundException::new);
    occupancies.remove(occupancy);
  }
}
