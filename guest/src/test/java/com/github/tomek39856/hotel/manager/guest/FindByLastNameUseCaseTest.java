package com.github.tomek39856.hotel.manager.guest;

import com.github.tomek39856.hotel.manager.guest.dto.GuestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindByLastNameUseCaseTest extends ComponentTest {
  @Autowired
  GuestRepository repository;
  @Autowired
  FindByLastNameUseCase useCase;

  @BeforeEach
  void setUp() {
    repository.clear();
  }

  @Test
  @DisplayName("should find guests with given last name")
  void shouldFindGuestsWithGivenLastName() {
    // given:
    repository.save(new Guest("Adam", "Nowak", UUID.randomUUID().toString()));
    repository.save(new Guest("Janusz", "Nowak", UUID.randomUUID().toString()));

    // when:
    List<GuestDto> result = useCase.execute("Nowak");

    // then:
    assertEquals(2, result.size());
  }
}