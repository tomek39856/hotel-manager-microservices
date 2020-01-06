package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.provider.ReservationProvider;
import com.github.tomek39856.hotel.manager.reservation.provider.dto.RoomReservationDto;
import org.springframework.stereotype.Service;
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
  public RoomReservationDto provide(@PathVariable("id") String id) {
    //return findReservationUseCase.execute(id);
    return null;
  }
}
