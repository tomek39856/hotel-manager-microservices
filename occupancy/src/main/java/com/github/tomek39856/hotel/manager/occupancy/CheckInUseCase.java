package com.github.tomek39856.hotel.manager.occupancy;

import com.github.tomek39856.hotel.manager.occupancy.dto.CheckInDto;
import com.github.tomek39856.hotel.manager.occupancy.event.out.UserCheckedInEvent;
import com.github.tomek39856.hotel.manager.occupancy.infrastructure.EventPublisher;
import com.github.tomek39856.hotel.manager.occupancy.infrastructure.UseCase;

@UseCase
class CheckInUseCase {
  private final RoomRepository roomRepository;
  private final EventPublisher eventPublisher;

  CheckInUseCase(RoomRepository roomRepository, EventPublisher eventPublisher) {
    this.roomRepository = roomRepository;
    this.eventPublisher = eventPublisher;
  }

  void execute(CheckInDto checkInDto) {
    roomRepository.findById(checkInDto.getRoomId())
        .orElseThrow(NotFoundException::new)
        .checkIn(checkInDto.getReservationId(), checkInDto.getFrom(), checkInDto.getTo());
    eventPublisher.publishEvent(new UserCheckedInEvent(checkInDto.getReservationId()));
  }
}
