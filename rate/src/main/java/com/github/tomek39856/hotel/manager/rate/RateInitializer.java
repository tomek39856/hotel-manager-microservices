package com.github.tomek39856.hotel.manager.rate;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Profile("local")
class RateInitializer implements InitializingBean {
  private final RoomRateRepository roomRateRepository;
  private final Clock clock;
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

  RateInitializer(RoomRateRepository roomRateRepository, Clock clock) {
    this.roomRateRepository = roomRateRepository;
    this.clock = clock;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    roomRateRepository.save(new Rate(RoomType.STANDARD, LocalDate.parse("11.10.1900", formatter), LocalDate.parse("11.10.2100", formatter), BigDecimal.valueOf(100), clock));
    roomRateRepository.save(new Rate(RoomType.QUEEN, LocalDate.parse("11.10.1900", formatter), LocalDate.parse("11.10.2100", formatter), BigDecimal.valueOf(100), clock));
    roomRateRepository.save(new Rate(RoomType.KING, LocalDate.parse("11.10.1900", formatter), LocalDate.parse("11.10.2100", formatter), BigDecimal.valueOf(100), clock));
  }
}
