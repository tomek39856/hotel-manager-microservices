package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.dto.SearchParametersDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/available-room")
class ReservableRoomController {
  private final FindFreeRoomsUseCase findFreeRoomsUseCase;

  ReservableRoomController(FindFreeRoomsUseCase findFreeRoomsUseCase) {
    this.findFreeRoomsUseCase = findFreeRoomsUseCase;
  }

  @GetMapping
  Set<RoomType> getAvailableRoomTypes(SearchParametersDto searchParametersDto) {
    return findFreeRoomsUseCase.execute(searchParametersDto);
  }
}
