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

class ConfirmArrivalUseCaseTest extends ComponentTest {
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  @Autowired
  ReservableRoomRepository reservableRoomRepository;
  @Autowired
  ConfirmArrivalUseCase confirmArrivalUseCase;

  @BeforeEach
  void setUp() {
    reservableRoomRepository.clear();
  }

  @Test
  @DisplayName("should confirm guest arrival when correct reservation id provided")
  void shouldConfirmGuestArrivalWhenCorrectReservationIdProvided() {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    ReservableRoom reservedRoom = new ReservableRoom(RoomType.KING);
    RoomReservation roomReservation = reservedRoom.reserve(startDate, endDate);
    reservableRoomRepository.save(reservedRoom);

    // when:
    confirmArrivalUseCase.execute(roomReservation.getId());

    // then:
    RoomReservation result = reservableRoomRepository.findRoomReservationByReservationId(roomReservation.getId()).get();
    assertEquals(ReservationStatus.ACTIVE, result.getStatus());
  }

  @Test
  @DisplayName("should throw when wrong reservation id provided")
  void shouldThrowWhenWrongReservationIdProvided() {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    String wrongReservationId = UUID.randomUUID().toString();

    // expect:
    assertThrows(NotFoundException.class, () -> confirmArrivalUseCase.execute(wrongReservationId));
  }
}