package com.github.tomek39856.hotel.manager.occupancy;

import com.github.tomek39856.hotel.manager.occupancy.dto.RoomDto;
import com.github.tomek39856.hotel.manager.occupancy.dto.SearchParametersDto;
import com.github.tomek39856.hotel.manager.occupancy.infrastructure.UseCase;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
class FindUnoccupiedRoomsUseCase {
  private final RoomRepository roomRepository;

  FindUnoccupiedRoomsUseCase(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  List<RoomDto> execute(SearchParametersDto params) {
    return roomRepository
        .findAvailableRoomsByTypeAndAvailableBetween(params.getRoomType(), params.getFrom(), params.getTo()).stream()
        .map(Room::toDto)
        .collect(Collectors.toList());
  }
}
