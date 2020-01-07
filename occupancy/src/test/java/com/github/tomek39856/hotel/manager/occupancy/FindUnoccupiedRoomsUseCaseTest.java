package com.github.tomek39856.hotel.manager.occupancy;

import com.github.tomek39856.hotel.manager.occupancy.dto.RoomDto;
import com.github.tomek39856.hotel.manager.occupancy.dto.SearchParametersDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindUnoccupiedRoomsUseCaseTest extends ComponentTest {
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  @Autowired
  RoomRepository roomRepository;
  @Autowired
  FindUnoccupiedRoomsUseCase findUnoccupiedRoomsUseCase;

  @BeforeEach
  void setUp() {
    roomRepository.clear();
  }

  @Test
  @DisplayName("should find available rooms in given time")
  void shouldFindAvailableRoomsInGivenTime() {
    // given:
    roomRepository.save(new Room(RoomType.KING, true, false, true));
    roomRepository.save(new Room(RoomType.KING, true, true, true));
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);

    // when:
    List<RoomDto> result = findUnoccupiedRoomsUseCase.execute(new SearchParametersDto(startDate, endDate, RoomType.KING));

    // then:
    assertEquals(2, result.size());
  }

  @Test
  @DisplayName("should skip reserved room types in given time")
  void shouldSkipReservedRoomTypesInGivenTime() {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    roomRepository.save(new Room(RoomType.KING, true, false, true));
    Room reservedRoom = new Room(RoomType.KING, true, true, true);
    reservedRoom.checkIn(UUID.randomUUID().toString(), startDate, endDate);
    roomRepository.save(reservedRoom);

    // when:
    List<RoomDto> result = findUnoccupiedRoomsUseCase.execute(new SearchParametersDto(startDate, endDate, RoomType.KING));

    // then:
    assertEquals(1, result.size());
  }
}