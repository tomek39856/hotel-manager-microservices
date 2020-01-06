package com.github.tomek39856.hotel.manager.guest;

import com.github.tomek39856.hotel.manager.guest.dto.GuestDto;
import com.github.tomek39856.hotel.manager.guest.infrastructure.UseCase;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
class FindByLastNameUseCase {
  private final GuestRepository guestRepository;

  FindByLastNameUseCase(GuestRepository guestRepository) {
    this.guestRepository = guestRepository;
  }

  List<GuestDto> execute(String lastName) {
    return guestRepository.findByLastName(lastName).stream()
        .map(Guest::toDto)
        .collect(Collectors.toList());
  }
}
