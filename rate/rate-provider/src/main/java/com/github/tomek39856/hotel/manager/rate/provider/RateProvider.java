package com.github.tomek39856.hotel.manager.rate.provider;

import com.github.tomek39856.hotel.manager.rate.provider.dto.RoomRate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;

@FeignClient("rate")
public interface RateProvider {
  @GetMapping("/itops/rate")
  RoomRate findRateAt(@RequestParam("roomType") String roomType,
                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("from") LocalDate from,
                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("to") LocalDate to,
                      @RequestParam("when") Instant when
  );
}
