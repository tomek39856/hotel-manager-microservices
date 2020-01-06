package com.github.tomek39856.hotel.manager.rate;

import com.github.tomek39856.hotel.manager.rate.dto.AvailableRoomTypeDto;
import com.github.tomek39856.hotel.manager.rate.dto.RoomRateDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
class RoomRateController {
  private final FindRateUseCase findRateUseCase;

  RoomRateController(FindRateUseCase findRateUseCase) {
    this.findRateUseCase = findRateUseCase;
  }

  @GetMapping
  RoomRateDto getCurrentAvailableRoomRate(AvailableRoomTypeDto availableRoomTypeDto) {
    return findRateUseCase.execute(availableRoomTypeDto, Instant.now());
  }

}
