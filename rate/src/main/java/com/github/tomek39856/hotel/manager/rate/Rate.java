package com.github.tomek39856.hotel.manager.rate;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

class Rate {
  private final String id = UUID.randomUUID().toString();
  private final RoomType roomType;
  private final LocalDate from;
  private final LocalDate to;
  private final BigDecimal dailyRate;
  private final Instant validFrom; // find with latest validFromDate before reservation date / could be stored in other placed atastore

  Rate(RoomType roomType, LocalDate from, LocalDate to, BigDecimal dailyRate, Clock clock) {
    this.roomType = roomType;
    this.from = from;
    this.to = to;
    this.dailyRate = dailyRate;
    this.validFrom = clock.instant();
  }

  String getId() {
    return id;
  }

  RoomType getRoomType() {
    return roomType;
  }

  LocalDate getFrom() {
    return from;
  }

  LocalDate getTo() {
    return to;
  }

  BigDecimal getDailyRate() {
    return dailyRate;
  }

  Instant getValidFrom() {
    return validFrom;
  }
}
