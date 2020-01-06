package com.github.tomek39856.hotel.manager.guest;

import com.github.tomek39856.hotel.manager.guest.dto.GuestDto;
import com.github.tomek39856.hotel.manager.guest.infrastructure.UseCase;

@UseCase
class FindForReservationUseCase {
  private final GuestRepository guestRepository;

  FindForReservationUseCase(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }

  GuestDto execute(String reservationId) {
    return guestRepository.findOneByReservationId(reservationId)
        .orElseThrow(NotFoundException::new)
        .toDto();
  }
}
