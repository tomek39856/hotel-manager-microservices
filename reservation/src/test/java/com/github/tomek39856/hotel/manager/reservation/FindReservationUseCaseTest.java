package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.dto.RoomReservationDto;
import com.github.tomek39856.hotel.manager.reservation.resolver.DateTimeFormatterResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(DateTimeFormatterResolver.class)
class FindReservationUseCaseTest extends ComponentTest {
  @Autowired
  ReservableRoomRepository reservableRoomRepository;
  @Autowired
  FindReservationUseCase findReservationUseCase;

  @BeforeEach
  void setUp() {
    reservableRoomRepository.clear();
  }

  @Test
  @DisplayName("should find reservation")
  void shouldFindReservation(DateTimeFormatter formatter) {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    ReservableRoom reservedRoom = new ReservableRoom(RoomType.KING);
    RoomReservation reservation = reservedRoom.reserve(startDate, endDate);
    reservableRoomRepository.save(reservedRoom);

    // when:
    RoomReservationDto result = findReservationUseCase.execute(reservation.getId());

    assertEquals(reservation.getId(), result.getId());
    assertEquals(RoomType.KING, result.getRoomType());
    assertEquals(startDate, result.getStart());
    assertEquals(endDate, result.getEnd());
  }

  @Test
  @DisplayName("should throw when not found")
  void shouldThrowWhenNotFound() {
    // expect:
    assertThrows(NotFoundException.class, () -> findReservationUseCase.execute(UUID.randomUUID().toString()));
  }
}