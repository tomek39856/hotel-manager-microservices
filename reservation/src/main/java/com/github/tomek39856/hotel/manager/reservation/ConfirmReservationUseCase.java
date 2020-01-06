package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.infrastructure.UseCase;

@UseCase
class ConfirmReservationUseCase {
  private final ReservableRoomRepository reservableRoomRepository;

  ConfirmReservationUseCase(ReservableRoomRepository reservableRoomRepository) {
    this.reservableRoomRepository = reservableRoomRepository;
  }

  void execute(String reservationId) {
    reservableRoomRepository.findRoomReservationByReservationId(reservationId)
        .orElseThrow(NotFoundException::new)
        .confirmReservation();
  }
}
