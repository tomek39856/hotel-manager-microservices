package com.github.tomek39856.hotel.manager.occupancy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CancelCheckInUseCaseTest  extends ComponentTest {
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  @Autowired
  RoomRepository roomRepository;
  @Autowired
  CancelCheckInUseCase useCase;

  @BeforeEach
  void setUp() {
    roomRepository.clear();
  }

  @Test
  @DisplayName(" should free room when correct id provided")
  void shouldFreeRoomWhenCorrectReservationIdProvided() {
    // given:
    String reservationId = UUID.randomUUID().toString();
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    Room room = new Room(RoomType.KING, false, true, false);
    room.checkIn(reservationId, startDate, endDate);
    roomRepository.save(room);

    // when:
    useCase.execute(reservationId);

    // then:
    Room result = roomRepository.findById(room.getId()).get();
    assertTrue(result.getOccupancies().isEmpty());
    assertTrue(result.isAvailable(startDate, endDate));
  }

}