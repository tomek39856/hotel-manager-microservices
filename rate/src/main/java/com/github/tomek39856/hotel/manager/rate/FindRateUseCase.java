package com.github.tomek39856.hotel.manager.rate;

import com.github.tomek39856.hotel.manager.rate.dto.AvailableRoomTypeDto;
import com.github.tomek39856.hotel.manager.rate.dto.RoomRateDto;
import com.github.tomek39856.hotel.manager.rate.infrastructure.UseCase;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

@UseCase
class FindRateUseCase {
  private final RoomRateRepository roomRateRepository;

  FindRateUseCase(RoomRateRepository roomRateRepository) {
    this.roomRateRepository = roomRateRepository;
  }

  RoomRateDto execute(AvailableRoomTypeDto room, Instant validAt) {
    return new RoomRateDto(
        daysBetween(room.getFrom(), room.getTo())
        .map(when -> roomRateRepository.findLatestByDayBetweenAndValidFromBeforeAndRoomType(when, validAt, room.getRoomType()))
        .map(rate -> rate.orElseThrow(InconsitentRatePeriodException::new).getDailyRate())
        .reduce(BigDecimal.ZERO, BigDecimal::add)
    );
  }

  private Stream<LocalDate> daysBetween(LocalDate from, LocalDate to) {
    return Stream.iterate(from, d -> d.plusDays(1))
        .limit(ChronoUnit.DAYS.between(from, to) + 1);
  }
}
