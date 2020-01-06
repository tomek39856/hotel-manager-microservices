package com.github.tomek39856.hotel.manager.rate;

import com.github.tomek39856.hotel.manager.rate.dto.AvailableRoomTypeDto;
import com.github.tomek39856.hotel.manager.rate.dto.RoomRateDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;

@RestController
public class ItopsRateController {
  private final FindRateUseCase findRateUseCase;

  public ItopsRateController(FindRateUseCase findRateUseCase) {
    this.findRateUseCase = findRateUseCase;
  }

  @GetMapping("/itops/rate")
  public RoomRateDto findRateAt(@RequestParam RoomType roomType, @RequestParam LocalDate from, @RequestParam LocalDate to, @RequestParam Instant when) {
    return findRateUseCase.execute(new AvailableRoomTypeDto(roomType, from, to), when);
  }
}
