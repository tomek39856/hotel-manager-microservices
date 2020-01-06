package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.dto.CreateRoomReservationDto;
import com.github.tomek39856.hotel.manager.reservation.dto.RoomReservationDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
class RoomReservationController {
  private final ReserveRoomUseCase reserveRoomUseCase;
  private final ConfirmArrivalUseCase confirmArrivalUseCase;
  private final FindReservationUseCase findReservationUseCase;

  RoomReservationController(ReserveRoomUseCase reserveRoomUseCase, ConfirmArrivalUseCase confirmArrivalUseCase, FindReservationUseCase findReservationUseCase) {
    this.reserveRoomUseCase = reserveRoomUseCase;
    this.confirmArrivalUseCase = confirmArrivalUseCase;
    this.findReservationUseCase = findReservationUseCase;
  }

  @GetMapping("/{id}")
  RoomReservationDto find(@PathVariable("id") String id) {
    return findReservationUseCase.execute(id);
  }

  @PostMapping
  RoomReservationDto reserveRoom(@RequestBody CreateRoomReservationDto createRoomReservationDto) {
    return reserveRoomUseCase
        .execute(createRoomReservationDto);
  }

  @PostMapping("/{id}/guest-arrival")
  void confirmGuestArrival(@PathVariable("id") String reservationId) {
    confirmArrivalUseCase.execute(reservationId);
  }
}
