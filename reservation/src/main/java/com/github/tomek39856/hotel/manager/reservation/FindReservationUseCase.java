package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.dto.RoomReservationDto;
import com.github.tomek39856.hotel.manager.reservation.infrastructure.UseCase;

@UseCase
class FindReservationUseCase {
  private final ReservableRoomRepository reservableRoomRepository;

  public FindReservationUseCase(ReservableRoomRepository reservableRoomRepository) {
    this.reservableRoomRepository = reservableRoomRepository;
  }

  public RoomReservationDto execute(String id) {
    return reservableRoomRepository.findRoomReservationByReservationId(id).orElseThrow(NotFoundException::new).toDto();
  }
}
