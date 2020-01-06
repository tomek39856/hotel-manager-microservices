package com.github.tomek39856.hotel.manager.rate;

import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class RoomRateRepository {
  private final Map<String, Rate> rates = new ConcurrentHashMap<>();

  Optional<Rate> findLatestByDayBetweenAndValidFromBeforeAndRoomType(LocalDate when, Instant validFrom, RoomType roomType) {
    return rates.values().stream()
        .filter(rate -> roomType == rate.getRoomType())
        .filter(rate -> validFrom.equals(rate.getValidFrom()) || rate.getValidFrom().isBefore(validFrom))
        .filter(rate -> isAfterOrEqual(when, rate.getFrom()) && when.isBefore(rate.getTo().plusDays(1)))
        .sorted((o1, o2) -> o1.getValidFrom().isBefore(o2.getValidFrom()) ? 1 : -1)
        .findFirst();
  }

  private boolean isAfterOrEqual(LocalDate when, LocalDate rateFrom) {
    return when.equals(rateFrom) || when.isAfter(rateFrom.minusDays(1));
  }

  public void save(Rate rate) {
    rates.put(rate.getId(), rate);
  }

  void clear() {
    this.rates.clear();
  }
}
