package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.dto.SearchParametersDto;
import com.github.tomek39856.hotel.manager.reservation.event.out.NoShowEvent;
import com.github.tomek39856.hotel.manager.reservation.event.out.TestEvent;
import com.github.tomek39856.hotel.manager.reservation.infrastructure.EventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/available-room")
class ReservableRoomController {
  private final FindFreeRoomsUseCase findFreeRoomsUseCase;
  private final EventPublisher eventPublisher;

  ReservableRoomController(FindFreeRoomsUseCase findFreeRoomsUseCase, EventPublisher eventPublisher) {
    this.findFreeRoomsUseCase = findFreeRoomsUseCase;
    this.eventPublisher = eventPublisher;
  }

  @GetMapping
  Set<RoomType> getAvailableRoomTypes(SearchParametersDto searchParametersDto) {
    eventPublisher.publishEvent(new NoShowEvent("123"));
    eventPublisher.publishEvent(new TestEvent("543"));
    return findFreeRoomsUseCase.execute(searchParametersDto);
  }
}
