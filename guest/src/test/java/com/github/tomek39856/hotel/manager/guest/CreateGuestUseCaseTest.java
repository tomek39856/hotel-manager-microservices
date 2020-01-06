package com.github.tomek39856.hotel.manager.guest;

import com.github.tomek39856.hotel.manager.guest.dto.CreateGuestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CreateGuestUseCaseTest extends ComponentTest {
  @Autowired
  GuestRepository repository;
  @Autowired
  CreateGuestUseCase useCase;

  @BeforeEach
  void setUp() {
    repository.clear();
  }

  @Test
  @DisplayName("should create guest")
  void shouldCreateGuest() {
    // given:
    String reservationId = UUID.randomUUID().toString();
    CreateGuestDto guestDto = new CreateGuestDto("Adam", "Nowak", reservationId);

    // when:
    useCase.execute(guestDto);

    // then:
    Optional<Guest> result = repository.findOneByReservationId(reservationId);
    assertTrue(result.isPresent());
    assertEquals("Adam", result.get().getFirstName());
    assertEquals("Nowak", result.get().getLastName());
    assertEquals(reservationId, result.get().getReservationId());
  }

  @DisplayName("should not create second time guest for the same reservation")
  void shouldNotCreateSecondGuestForSameReservation() {
    // given:
    String reservationId = UUID.randomUUID().toString();
    CreateGuestDto guestDto = new CreateGuestDto("Adam", "Nowak", reservationId);

    // when:
    useCase.execute(guestDto);
    useCase.execute(guestDto);

    // then:
    assertEquals(1, repository.findByLastName("Nowak"));
  }
}