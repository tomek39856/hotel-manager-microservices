package com.github.tomek39856.hotel.manager.guest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CreateGuestDto {
  private final String firstName;
  private final String lastName;
  private final String reservationId;

  @JsonCreator
  public CreateGuestDto(String firstName, String lastName, String reservationId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.reservationId = reservationId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getReservationId() {
    return reservationId;
  }
}
