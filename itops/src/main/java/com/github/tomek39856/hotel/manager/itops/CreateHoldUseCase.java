package com.github.tomek39856.hotel.manager.itops;

import com.github.tomek39856.hotel.manager.itops.dto.PaymentInformationDto;
import com.github.tomek39856.hotel.manager.itops.event.out.HoldCreatedEvent;
import com.github.tomek39856.hotel.manager.itops.event.out.HoldFailedEvent;
import com.github.tomek39856.hotel.manager.itops.infrastructure.EventPublisher;
import com.github.tomek39856.hotel.manager.rate.provider.RateProvider;
import com.github.tomek39856.hotel.manager.rate.provider.dto.RoomRate;
import com.github.tomek39856.hotel.manager.reservation.provider.ReservationProvider;
import com.github.tomek39856.hotel.manager.reservation.provider.dto.RoomReservation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
 class CreateHoldUseCase {

  private final RateProvider rateProvider;
  private final ReservationProvider reservationProvider;
  private final EventPublisher eventPublisher;
  private final List<String> createdHolds = new ArrayList<>();
  private final ExternalCardService externalCardService;

   CreateHoldUseCase(RateProvider rateProvider, ReservationProvider reservationProvider, EventPublisher eventPublisher, ExternalCardService externalCardService) {
    this.rateProvider = rateProvider;
    this.reservationProvider = reservationProvider;
    this.eventPublisher = eventPublisher;
    this.externalCardService = externalCardService;
   }

   void create(PaymentInformationDto payment) {
    if(createdHolds.contains(payment.getId())) {
      return;
    } else {
      createdHolds.add(payment.getId());
    }
    RoomReservation reservation = reservationProvider.provide(payment.getReservationId());
    RoomRate rate = rateProvider.findRateAt(reservation.getRoomType(), reservation.getStart(), reservation.getEnd(), reservation.getReservedAt());
    try {
      externalCardService.hold(payment.getCard().getOwner(), payment.getCard().getNumber(), payment.getCard().getValidityDate(), rate.getSum());
      eventPublisher.publishEvent(new HoldCreatedEvent(payment.getId()));
    } catch (Exception e) {
      eventPublisher.publishEvent(new HoldFailedEvent(payment.getId()));
    }
  }
}
