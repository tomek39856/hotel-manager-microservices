package com.github.tomek39856.hotel.manager.reservation;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
class NoShowReservationCanceller {
  private static final int MAX_DELAY_IN_DAYS = 2;

  private final GuestNoShowUseCase guestNoShowUseCase;
  private final ReservableRoomRepository reservableRoomRepository;
  private final Clock clock;

  NoShowReservationCanceller(GuestNoShowUseCase guestNoShowUseCase, ReservableRoomRepository reservableRoomRepository, Clock clock) {
    this.guestNoShowUseCase = guestNoShowUseCase;
    this.reservableRoomRepository = reservableRoomRepository;
    this.clock = clock;
  }

  @Scheduled(cron = "0 * * * * *")
  void cancelNoShowReservations() {
    reservableRoomRepository.findReservationIdByStatusConfirmedAndStartBefore(getReservationExpirationDate()).stream()
        .forEach(guestNoShowUseCase::execute);
  }

  private LocalDate getReservationExpirationDate() {
    return LocalDateTime.ofInstant(clock.instant(), ZoneId.systemDefault()).toLocalDate().minusDays(MAX_DELAY_IN_DAYS);
  }
}
