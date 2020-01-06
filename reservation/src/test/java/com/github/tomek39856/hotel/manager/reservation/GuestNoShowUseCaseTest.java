package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.event.NoShowEvent;
import com.github.tomek39856.hotel.manager.reservation.infrastructure.Event;
import com.github.tomek39856.hotel.manager.reservation.infrastructure.EventPublisher;
import com.github.tomek39856.hotel.manager.reservation.resolver.DateTimeFormatterResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DateTimeFormatterResolver.class)
class GuestNoShowUseCaseTest extends ComponentTest {
  @Autowired
  ReservableRoomRepository reservableRoomRepository;
  @Autowired
  GuestNoShowUseCase guestNoShowUseCase;
  @MockBean
  EventPublisher eventPublisher;
  @Captor
  protected ArgumentCaptor<Event> publishEventCaptor;

  @BeforeEach
  void setUp() {
    reservableRoomRepository.clear();
  }

  @Test
  @DisplayName("should cancel reservation and emit no show event")
  void shouldCancelReservationAndEmitNoShowEvent(DateTimeFormatter formatter) {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    ReservableRoom reservedRoom = new ReservableRoom(RoomType.KING);
    RoomReservation roomReservation = reservedRoom.reserve(startDate, endDate);
    reservableRoomRepository.save(reservedRoom);

    // when:
    guestNoShowUseCase.execute(roomReservation.getId());

    // then:
    RoomReservation result = reservableRoomRepository.findRoomReservationByReservationId(roomReservation.getId()).get();
    assertEquals(ReservationStatus.CANCELLED, result.getStatus());
    Mockito.verify(eventPublisher).publishEvent(publishEventCaptor.capture());
    assertTrue(publishEventCaptor.getValue() instanceof NoShowEvent);
    assertEquals(roomReservation.getId(), ((NoShowEvent)publishEventCaptor.getValue()).getReservationId());
  }

  @Test
  @DisplayName("should throw when wrong reservation id provided")
  void shouldThrowWhenWrongReservationIdProvided() {
    // given:
    String wrongReservationId = UUID.randomUUID().toString();

    // expect:
    assertThrows(NotFoundException.class, () -> guestNoShowUseCase.execute(wrongReservationId));
  }
}