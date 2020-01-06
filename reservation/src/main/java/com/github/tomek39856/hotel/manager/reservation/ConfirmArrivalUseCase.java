package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.infrastructure.UseCase;

@UseCase
class ConfirmArrivalUseCase {
  private final ReservableRoomRepository reservableRoomRepository;

  ConfirmArrivalUseCase(ReservableRoomRepository reservableRoomRepository) {
    this.reservableRoomRepository = reservableRoomRepository;
  }

  public void execute(String reservationId) {
    reservableRoomRepository.findRoomReservationByReservationId(reservationId)
        .orElseThrow(NotFoundException::new)
        .confirmGuestArrival();
  }
}
