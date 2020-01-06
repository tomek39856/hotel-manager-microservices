package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.dto.CreateRoomReservationDto;
import com.github.tomek39856.hotel.manager.reservation.dto.RoomReservationDto;
import com.github.tomek39856.hotel.manager.reservation.resolver.DateTimeFormatterResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DateTimeFormatterResolver.class)
class ReserveRoomUseCaseTest extends ComponentTest {
  @Autowired
  ReservableRoomRepository reservableRoomRepository;
  @Autowired
  ReserveRoomUseCase reserveRoomUseCase;

  @BeforeEach
  void setUp() {
    reservableRoomRepository.clear();
  }

  @Test
  @DisplayName("should reserve room when correct dates provided")
  void shouldReserveRoomWhenCorrectDatesProvided(DateTimeFormatter formatter) {
    // given:
    ReservableRoom room = new ReservableRoom(RoomType.KING);
    reservableRoomRepository.save(room);
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);

    // when:
    RoomReservationDto result = reserveRoomUseCase.execute(new CreateRoomReservationDto(RoomType.KING, startDate, endDate));

    // then:
    ReservableRoom persistedRoom = reservableRoomRepository.findOneById(room.getId()).get();
    assertEquals(1, persistedRoom.getRoomReservations().size());
    Optional<RoomReservation> persistedReservation = reservableRoomRepository.findRoomReservationByReservationId(result.getId());
    assertTrue(persistedReservation.isPresent());
    assertEquals(startDate, persistedReservation.get().getStart());
    assertEquals(endDate, persistedReservation.get().getEnd());
  }

  @Test
  @DisplayName("should throw when reserving on overlapping date")
  void shouldThrowWhenReservingOnOverlappingDate(DateTimeFormatter formatter) {
    // given:
    ReservableRoom room = new ReservableRoom(RoomType.KING);
    reservableRoomRepository.save(room);
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    LocalDate startOverlappingDate = LocalDate.parse("1988-10-12", formatter);
    LocalDate endOverlappingDate = LocalDate.parse("1988-10-13", formatter);

    // expect:
    reserveRoomUseCase.execute(new CreateRoomReservationDto(RoomType.KING, startDate, endDate));
    assertThrows(NotFoundException.class, () -> reserveRoomUseCase.execute(new CreateRoomReservationDto(RoomType.KING, startOverlappingDate, endOverlappingDate)));
  }

  @Test
  @DisplayName("should throw when no room available")
  void shouldThrowWhenNoRoomAvailable(DateTimeFormatter formatter) {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);

    // expect:
    assertThrows(NotFoundException.class, () -> reserveRoomUseCase.execute(new CreateRoomReservationDto(RoomType.KING, startDate, endDate)));
  }
}