package com.github.tomek39856.hotel.manager.occupancy;


import com.github.tomek39856.hotel.manager.occupancy.infrastructure.UseCase;

@UseCase
class CancelCheckInUseCase {
  private final RoomRepository roomRepository;

  CancelCheckInUseCase(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  void execute(String reservationId) {
    roomRepository.findByOccupancyReservationId(reservationId)
        .ifPresent(room -> room.cancelCheckIn(reservationId));
  }
}
