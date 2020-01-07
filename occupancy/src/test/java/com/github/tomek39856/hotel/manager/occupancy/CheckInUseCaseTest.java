package com.github.tomek39856.hotel.manager.occupancy;

import com.github.tomek39856.hotel.manager.occupancy.dto.CheckInDto;
import com.github.tomek39856.hotel.manager.occupancy.event.out.UserCheckedInEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CheckInUseCaseTest extends EventPublishingComponentTest {
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  @Autowired
  RoomRepository roomRepository;
  @Autowired
  CheckInUseCase checkInUseCase;

  @BeforeEach
  void setUp() {
    roomRepository.clear();
  }

  @Test
  @DisplayName("should check in guest when room is free")
  void shouldCheckInGuestWhenRoomIsFree() {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    String reservationId = UUID.randomUUID().toString();
    Room room = new Room(RoomType.KING, false, true, false);
    roomRepository.save(room);
    CheckInDto checkInDto = new CheckInDto(reservationId, room.getId(), startDate, endDate);

    // when:
    checkInUseCase.execute(checkInDto);

    // then:
    Room result = roomRepository.findById(room.getId()).get();
    assertFalse(result.isAvailable(startDate, endDate));
    Mockito.verify(eventPublisher).publishEvent(publishEventCaptor.capture());
    assertTrue(publishEventCaptor.getValue() instanceof UserCheckedInEvent);
    assertEquals(reservationId, ((UserCheckedInEvent)publishEventCaptor.getValue()).getReservationId());
  }

  @Test
  @DisplayName("should throw when room is not free")
  void shouldThrowWhenRoomIsNotFree() {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    String reservationId = UUID.randomUUID().toString();
    Room room = new Room(RoomType.KING, false, true, false);
    roomRepository.save(room);
    CheckInDto checkInDto = new CheckInDto(reservationId, room.getId(), startDate, endDate);

    // expect:
    checkInUseCase.execute(checkInDto);
    assertThrows(ConflictException.class, () -> checkInUseCase.execute(checkInDto));
  }

  @Test
  @DisplayName("should throw when room not found")
  void shouldThrowWhenRoomNotFound() {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    String reservationId = UUID.randomUUID().toString();
    String wrongId = UUID.randomUUID().toString();
    CheckInDto checkInDto = new CheckInDto(reservationId, wrongId, startDate, endDate);

    // expect:
    assertThrows(NotFoundException.class, () -> checkInUseCase.execute(checkInDto));
  }
}