package com.github.tomek39856.hotel.manager.reservation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfirmReservationUseCaseTest extends ComponentTest {
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  @Autowired
  ReservableRoomRepository reservableRoomRepository;
  @Autowired
  ConfirmReservationUseCase confirmReservationUseCase;

  @BeforeEach
  void setUp() {
    reservableRoomRepository.clear();
  }

  @Test
  @DisplayName("should confirm reservation when correct reservation id provided")
  void shouldConfirmReservationWhenCorrectReservationIdProvided() {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    ReservableRoom reservedRoom = new ReservableRoom(RoomType.KING);
    RoomReservation roomReservation = reservedRoom.reserve(startDate, endDate);
    reservableRoomRepository.save(reservedRoom);

    // when:
    confirmReservationUseCase.execute(roomReservation.getId());

    // then:
    RoomReservation result = reservableRoomRepository.findRoomReservationByReservationId(roomReservation.getId()).get();
    assertEquals(ReservationStatus.CONFIRMED, result.getStatus());
  }

  @Test
  @DisplayName("should throw when wrong reservation id provided")
  void shouldThrowWhenWrongReservationIdProvided() {
    // given:
    String wrongReservationId = UUID.randomUUID().toString();

    // expect:
    assertThrows(NotFoundException.class, () -> confirmReservationUseCase.execute(wrongReservationId));
  }
}