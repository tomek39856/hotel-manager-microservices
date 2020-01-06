package com.github.tomek39856.hotel.manager.reservation.provider.dto;

public enum ReservationStatus {
  NEW, // may be abadonned during process
  CONFIRMED, // with card hold ready to charge cancellation fee
  ACTIVE,
  CANCELLED
}
