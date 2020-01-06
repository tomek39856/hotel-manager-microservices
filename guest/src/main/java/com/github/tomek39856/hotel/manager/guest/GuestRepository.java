package com.github.tomek39856.hotel.manager.guest;

import com.github.tomek39856.hotel.manager.guest.Guest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
class GuestRepository {
  private final Map<String, Guest> guests = new ConcurrentHashMap<>();

  void save(Guest guest) {
    this.guests.put(guest.getId(), guest);
  }

  Optional<Guest> findOneByReservationId(String reservationId) {
    return guests.values().stream()
        .filter(guest -> reservationId.equals(guest.getReservationId()))
        .findAny();
  }

  List<Guest> findByLastName(String lastName) {
    return guests.values().stream()
        .filter(guest -> lastName.equals(guest.getLastName()))
        .collect(Collectors.toList());
  }

  void clear() {
    guests.clear();
  }
}
