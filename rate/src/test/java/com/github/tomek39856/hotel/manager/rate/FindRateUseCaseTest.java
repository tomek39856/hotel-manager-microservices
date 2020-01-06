package com.github.tomek39856.hotel.manager.rate;

import com.github.tomek39856.hotel.manager.rate.dto.AvailableRoomTypeDto;
import com.github.tomek39856.hotel.manager.rate.dto.RoomRateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FindRateUseCaseTest extends ComponentTest {
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  @Autowired
  RoomRateRepository roomRateRepository;
  @Autowired
  FindRateUseCase findRateUseCase;

  @BeforeEach
  void setUp() {
    roomRateRepository.clear();
  }

  @Test
  @DisplayName("should calculate rate for given period")
  void shouldCalculateRateForGivenPeriod() {
    // given:
    roomRateRepository.save(new Rate(
            RoomType.STANDARD,
            LocalDate.parse("1988-10-11", formatter),
            LocalDate.parse("1988-10-15", formatter),
            BigDecimal.valueOf(100),
            getFixedClock("1998-10-11")
        )
    );
    roomRateRepository.save(new Rate(
            RoomType.STANDARD,
            LocalDate.parse("1988-10-15", formatter),
            LocalDate.parse("1988-10-19", formatter),
            BigDecimal.valueOf(200),
            getFixedClock("1998-10-12")
        )
    );

    // when:
    RoomRateDto result = findRateUseCase.execute(new AvailableRoomTypeDto(
            RoomType.STANDARD,
            LocalDate.parse("1988-10-11", formatter),
            LocalDate.parse("1988-10-18", formatter)),
            Instant.now()
    );

    // then:
    assertEquals(BigDecimal.valueOf(1200), result.getSum());
  }

  @Test
  @DisplayName("should take rate for given point in time")
  public void shouldTakeRateForGivenPointInTime() {
    // given:
    roomRateRepository.save(new Rate(
            RoomType.STANDARD,
            LocalDate.parse("1988-10-11", formatter),
            LocalDate.parse("1988-10-15", formatter),
            BigDecimal.valueOf(100),
            getFixedClock("1998-10-11")
        )
    );
    roomRateRepository.save(new Rate(
            RoomType.STANDARD,
            LocalDate.parse("1988-10-11", formatter),
            LocalDate.parse("1988-10-15", formatter),
            BigDecimal.valueOf(200),
            getFixedClock("1998-10-13")
        )
    );

    // when:
    RoomRateDto result = findRateUseCase.execute(new AvailableRoomTypeDto(
            RoomType.STANDARD,
            LocalDate.parse("1988-10-11", formatter),
            LocalDate.parse("1988-10-15", formatter)),
            getFixedClock("1998-10-12").instant()
    );

    // then:
    assertEquals(BigDecimal.valueOf(500), result.getSum());
  }

  @Test
  @DisplayName("should throw when missing rate values")
  void shouldThrowWhenMissingRateValues() {
    // given:
    roomRateRepository.save(new Rate(
            RoomType.STANDARD,
            LocalDate.parse("1988-10-11", formatter),
            LocalDate.parse("1988-10-15", formatter),
            BigDecimal.valueOf(100),
            getFixedClock("1998-10-11")
        )
    );
    roomRateRepository.save(new Rate(
            RoomType.STANDARD,
            LocalDate.parse("1988-10-18", formatter),
            LocalDate.parse("1988-10-19", formatter),
            BigDecimal.valueOf(200),
            getFixedClock("1998-10-12")
        )
    );

    // expect:
    assertThrows(InconsitentRatePeriodException.class, () -> findRateUseCase.execute(new AvailableRoomTypeDto(
            RoomType.STANDARD,
            LocalDate.parse("1988-10-11", formatter),
            LocalDate.parse("1988-10-18", formatter)),
        Instant.now()
    ));
  }

  private Clock getFixedClock(String s) {
    return Clock.fixed(LocalDate.parse(s, formatter).atTime(15, 00).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
  }
}