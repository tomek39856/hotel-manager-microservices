package com.github.tomek39856.hotel.manager.reservation;

import com.github.tomek39856.hotel.manager.reservation.infrastructure.EventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class NoShowReservationCancellerTest extends ComponentTest {
  @Autowired
  ReservableRoomRepository reservableRoomRepository;
  @Autowired
  NoShowReservationCanceller noShowReservationCanceller;
  @MockBean
  Clock clock;
  @MockBean
  EventPublisher eventPublisher;

  @BeforeEach
  void setUp() {
    reservableRoomRepository.clear();
  }

  @Test
  @DisplayName("should cancel expired reservations")
  void shouldCancelExpiredReservations() {
    // given:
    when(clock.instant()).thenReturn(Instant.now());
    ReservableRoom room = new ReservableRoom(RoomType.KING);
    RoomReservation roomReservation = room.reserve(LocalDate.now(), LocalDate.now().plusDays(3));
    roomReservation.confirmReservation();
    reservableRoomRepository.save(room);

    // when:
    jumpInTime(3);
    noShowReservationCanceller.cancelNoShowReservations();

    // then:
    RoomReservation result = reservableRoomRepository.findRoomReservationByReservationId(roomReservation.getId()).get();
    assertEquals(ReservationStatus.CANCELLED, result.getStatus());
  }

  @Test
  @DisplayName("should not cancel not yet expired reservations")
  void shouldNotCancelNotYetExpiredReservations() {
    // given:
    when(clock.instant()).thenReturn(Instant.now());
    ReservableRoom room = new ReservableRoom(RoomType.KING);
    RoomReservation roomReservation = room.reserve(LocalDate.now(), LocalDate.now().plusDays(3));
    roomReservation.confirmReservation();
    reservableRoomRepository.save(room);

    // when:
    jumpInTime(1);
    noShowReservationCanceller.cancelNoShowReservations();

    // then:
    RoomReservation result = reservableRoomRepository.findRoomReservationByReservationId(roomReservation.getId()).get();
    assertEquals(ReservationStatus.CONFIRMED, result.getStatus());
  }

  @Test
  @DisplayName("should not cancel not confirmed reservations")
  void shouldNotCancelNotConfirmedReservations() {
    // given:
    when(clock.instant()).thenReturn(Instant.now());
    ReservableRoom room = new ReservableRoom(RoomType.KING);
    RoomReservation roomReservation = room.reserve(LocalDate.now(), LocalDate.now().plusDays(3));
    reservableRoomRepository.save(room);

    // when:
    jumpInTime(3);
    noShowReservationCanceller.cancelNoShowReservations();

    // then:
    RoomReservation result = reservableRoomRepository.findRoomReservationByReservationId(roomReservation.getId()).get();
    assertEquals(ReservationStatus.NEW, result.getStatus());
  }

  private void jumpInTime(int days) {
    when(clock.instant()).thenReturn(Instant.now().plusSeconds(days * 3600 * 24));
  }
}