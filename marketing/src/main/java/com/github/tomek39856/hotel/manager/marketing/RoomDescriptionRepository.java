package com.github.tomek39856.hotel.manager.marketing;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
class RoomDescriptionRepository {
  private final Set<RoomDescription> roomDescriptions = new HashSet<>();

  Optional<RoomDescription> findOne(RoomType roomType) {
    return roomDescriptions.stream()
      .filter(roomDescription -> roomType == roomDescription.getRoomType())
      .findAny();
  }

  void save(RoomDescription roomDescription) {
    roomDescriptions.add(roomDescription);
  }

  void clear() {
    roomDescriptions.clear();
  }
}
