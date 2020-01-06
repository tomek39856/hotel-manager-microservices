package com.github.tomek39856.hotel.manager.rate.provider;

import com.github.tomek39856.hotel.manager.rate.provider.dto.RoomRateDto;
import com.github.tomek39856.hotel.manager.rate.provider.dto.RoomType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;

@FeignClient("rate")
public interface RateProvider {
  @GetMapping("/itops/rate")
  RoomRateDto findRateAt(@RequestParam("roomType") String roomType, @RequestParam("from") LocalDate from, @RequestParam("to") LocalDate to, @RequestParam("when") Instant when);
}
