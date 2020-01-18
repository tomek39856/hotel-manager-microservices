package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.provider.ReservationProvider;
import com.github.tomek39856.hotel.manager.reservation.provider.dto.RoomReservation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItopsReservationController implements ReservationProvider {
  private final FindReservationUseCase findReservationUseCase;

  public ItopsReservationController(FindReservationUseCase findReservationUseCase) {
    this.findReservationUseCase = findReservationUseCase;
  }

  @GetMapping("/itops/reservation/{id}")
  public RoomReservation provide(@PathVariable("id") String id) {
    com.github.tomek39856.hotel.manager.reservation.dto.RoomReservationDto roomReservationDto = findReservationUseCase.execute(id);
    return new RoomReservation(
        roomReservationDto.getId(),
        roomReservationDto.getStart(),
        roomReservationDto.getEnd(),
        roomReservationDto.getReservedAt(),
        roomReservationDto.getRoomType().name()
    );
  }
}
