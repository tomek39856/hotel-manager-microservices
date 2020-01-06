package com.github.tomek39856.hotel.manager.guest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class GuestDto {
  private final String id;
  private final String firstName;
  private final String lastName;
  private final String reservationId;

  @JsonCreator
  public GuestDto(String id, String firstName, String lastName, String reservationId) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.reservationId = reservationId;
  }
}
