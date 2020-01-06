package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.dto.SearchParametersDto;
import com.github.tomek39856.hotel.manager.reservation.resolver.DateTimeFormatterResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(DateTimeFormatterResolver.class)
class FindFreeRoomsUseCaseTest extends ComponentTest {
  @Autowired
  ReservableRoomRepository reservableRoomRepository;
  @Autowired
  FindFreeRoomsUseCase findFreeRoomsUseCase;

  @BeforeEach
  void setUp() {
    reservableRoomRepository.clear();
  }

  @Test
  @DisplayName("should find available room types in given time")
  void shouldFindAvailableRoomTypesInGivenTime(DateTimeFormatter formatter) {
    // given:
    reservableRoomRepository.save(new ReservableRoom(RoomType.KING));
    reservableRoomRepository.save(new ReservableRoom(RoomType.QUEEN));
    reservableRoomRepository.save(new ReservableRoom(RoomType.STANDARD));
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);

    // when:
    Set<RoomType> result = findFreeRoomsUseCase.execute(new SearchParametersDto(startDate, endDate));

    // then:
    assertEquals(3, result.size());
    assertTrue(result.containsAll(Arrays.asList(RoomType.KING, RoomType.QUEEN, RoomType.STANDARD)));
  }

  @Test
  @DisplayName("should skip reserved room types in given time")
  void shouldSkipReservedRoomTypesInGivenTime(DateTimeFormatter formatter) {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    reservableRoomRepository.save(RoomFixture.reservedRoom(startDate, endDate));
    reservableRoomRepository.save(new ReservableRoom(RoomType.QUEEN));
    reservableRoomRepository.save(new ReservableRoom(RoomType.STANDARD));

    // when:
    Set<RoomType> result = findFreeRoomsUseCase.execute(new SearchParametersDto(startDate, endDate));

    // then:
    assertEquals(2, result.size());
    assertTrue(result.containsAll(Arrays.asList(RoomType.QUEEN, RoomType.STANDARD)));
  }

  @Test
  @DisplayName("should not skip reserved room types in given time when resrevation is cancelled")
  void shouldNotSkipReservedRoomTypesInGivenTimeWhenReservationIsCancelled(DateTimeFormatter formatter) {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    reservableRoomRepository.save(RoomFixture.roomWithCanceledReservation(startDate, endDate));
    reservableRoomRepository.save(new ReservableRoom(RoomType.QUEEN));
    reservableRoomRepository.save(new ReservableRoom(RoomType.STANDARD));

    // when:
    Set<RoomType> result = findFreeRoomsUseCase.execute(new SearchParametersDto(startDate, endDate));

    // then:
    assertEquals(3, result.size());
    assertTrue(result.containsAll(Arrays.asList(RoomType.QUEEN, RoomType.STANDARD)));
  }
}