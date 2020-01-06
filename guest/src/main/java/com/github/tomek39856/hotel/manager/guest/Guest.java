package com.github.tomek39856.hotel.manager.guest;

import com.github.tomek39856.hotel.manager.guest.dto.CreateGuestDto;
import com.github.tomek39856.hotel.manager.guest.dto.GuestDto;

import java.util.UUID;

class Guest {
  private final String id = UUID.randomUUID().toString();
  private final String firstName;
  private final String lastName;
  private final String reservationId;

  Guest(String firstName, String lastName, String reservationId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.reservationId = reservationId;
  }

  String getId() {
    return id;
  }

  String getReservationId() {
    return reservationId;
  }

  String getFirstName() {
    return firstName;
  }

  String getLastName() {
    return lastName;
  }

  static Guest ofDto(CreateGuestDto createGuestDto) {
    return new Guest(
        createGuestDto.getFirstName(),
        createGuestDto.getLastName(),
        createGuestDto.getReservationId()
    );
  }

  GuestDto toDto() {
    return new GuestDto(
        id,
        firstName,
        lastName,
        reservationId
    );
  }
}
