package com.github.tomek39856.hotel.manager.guest;

import com.github.tomek39856.hotel.manager.guest.CreateGuestUseCase;
import com.github.tomek39856.hotel.manager.guest.FindByLastNameUseCase;
import com.github.tomek39856.hotel.manager.guest.FindForReservationUseCase;
import com.github.tomek39856.hotel.manager.guest.dto.CreateGuestDto;
import com.github.tomek39856.hotel.manager.guest.dto.GuestDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
class GuestController {
  private final CreateGuestUseCase createGuestUseCase;
  private final FindByLastNameUseCase findByLastNameUseCase;
  private final FindForReservationUseCase findForReservationUseCase;

  GuestController(CreateGuestUseCase createGuestUseCase, FindByLastNameUseCase findByLastNameUseCase, FindForReservationUseCase findForReservationUseCase) {
    this.createGuestUseCase = createGuestUseCase;
    this.findByLastNameUseCase = findByLastNameUseCase;
    this.findForReservationUseCase = findForReservationUseCase;
  }

  @PostMapping
  void createGuest(@RequestBody CreateGuestDto createGuestDto) {
    this.createGuestUseCase.execute(createGuestDto);
  }

  @GetMapping(params = {"lastName"})
  List<GuestDto> findGuestsByLastName(@RequestParam("lastName") String lastName) {
    return findByLastNameUseCase.execute(lastName);
  }

  @GetMapping(params = {"reservationId"})
  GuestDto findGuestsByReservation(@RequestParam("reservationId") String reservationId) {
    return findForReservationUseCase.execute(reservationId);
  }
}
