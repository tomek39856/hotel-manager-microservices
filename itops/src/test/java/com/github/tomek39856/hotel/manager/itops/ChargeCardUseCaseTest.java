package com.github.tomek39856.hotel.manager.itops;

import com.github.tomek39856.hotel.manager.itops.dto.CardDto;
import com.github.tomek39856.hotel.manager.itops.dto.PaymentInformationDto;
import com.github.tomek39856.hotel.manager.itops.event.out.CardChargedEvent;
import com.github.tomek39856.hotel.manager.itops.event.out.ChargeCardFailedEvent;
import com.github.tomek39856.hotel.manager.rate.provider.RateProvider;
import com.github.tomek39856.hotel.manager.rate.provider.dto.RoomRateDto;
import com.github.tomek39856.hotel.manager.reservation.provider.ReservationProvider;
import com.github.tomek39856.hotel.manager.reservation.provider.dto.ReservationStatus;
import com.github.tomek39856.hotel.manager.reservation.provider.dto.RoomReservationDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChargeCardUseCaseTest extends EventPublishingComponentTest {
  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  @Autowired
  ChargeCardUseCase chargeCardUseCase;
  @MockBean
  ExternalCardService externalCardService;
  @MockBean
  RateProvider rateProvider;
  @MockBean
  ReservationProvider reservationProvider;

  @Test
  @DisplayName("should charge card and send event when payment was not proceeded before")
  void shouldChargeCardAndSendEventWhenPaymentWasNotProceededBefore() {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    String reservationId = UUID.randomUUID().toString();
    CardDto cardDto = new CardDto("Adam Nowak", "123456", LocalDate.now());
    Instant reservedAt = Instant.now();
    RoomReservationDto roomReservationDto = new RoomReservationDto(
        reservationId,
        startDate,
        endDate,
        reservedAt,
        ReservationStatus.ACTIVE,
        "STANDARD"
    );
    RoomRateDto roomRateDto = new RoomRateDto(BigDecimal.valueOf(100));
    PaymentInformationDto paymentInformationDto = new PaymentInformationDto(UUID.randomUUID().toString(), reservationId, cardDto);
    Mockito.when(reservationProvider.provide(reservationId)).thenReturn(roomReservationDto);
    Mockito.when(rateProvider.findRateAt("STANDARD", startDate, endDate, reservedAt)).thenReturn(roomRateDto);

    // when:
    chargeCardUseCase.charge(paymentInformationDto, 100);

    // then:
    Mockito.verify(eventPublisher).publishEvent(publishEventCaptor.capture());
    assertTrue(publishEventCaptor.getValue() instanceof CardChargedEvent);
    assertEquals(paymentInformationDto.getId(), ((CardChargedEvent) publishEventCaptor.getValue()).getPaymentId());
  }

  @Test
  @DisplayName("should send charge failed event when payment failed")
  void shouldChargeSendChargeFailedEventWhenPaymentFailed() {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    String reservationId = UUID.randomUUID().toString();
    String cardOwner = "Adam Nowak";
    String cardNumber = "123456";
    LocalDate cardValidityDate = LocalDate.now();
    CardDto cardDto = new CardDto(cardOwner, cardNumber, cardValidityDate);
    Instant reservedAt = Instant.now();
    RoomReservationDto roomReservationDto = new RoomReservationDto(
        reservationId,
        startDate,
        endDate,
        reservedAt,
        ReservationStatus.ACTIVE,
        "STANDARD"
    );
    RoomRateDto roomRateDto = new RoomRateDto(BigDecimal.valueOf(100));
    PaymentInformationDto paymentInformationDto = new PaymentInformationDto(UUID.randomUUID().toString(), reservationId, cardDto);
    Mockito.when(reservationProvider.provide(reservationId)).thenReturn(roomReservationDto);
    Mockito.when(rateProvider.findRateAt("STANDARD", startDate, endDate, reservedAt)).thenReturn(roomRateDto);
    Mockito.doThrow(new RuntimeException()).when(externalCardService).charge(cardOwner, cardNumber, cardValidityDate, BigDecimal.valueOf(100));

    // when:
    chargeCardUseCase.charge(paymentInformationDto, 100);

    // then:
    Mockito.verify(eventPublisher).publishEvent(publishEventCaptor.capture());
    assertTrue(publishEventCaptor.getValue() instanceof ChargeCardFailedEvent);
    assertEquals(paymentInformationDto.getId(), ((ChargeCardFailedEvent) publishEventCaptor.getValue()).getPaymentId());
  }

  @Test
  @DisplayName("should deduplicate payments with the same id")
  void shouldDeduplicatePaymentsWithTheSameId() {
    // given:
    LocalDate startDate = LocalDate.parse("1988-10-11", formatter);
    LocalDate endDate = LocalDate.parse("1988-10-16", formatter);
    String reservationId = UUID.randomUUID().toString();
    String owner = "Adam Nowak";
    String cardNr = "123456";
    LocalDate validityDate = LocalDate.now();
    CardDto cardDto = new CardDto(owner, cardNr, validityDate);
    Instant reservedAt = Instant.now();
    RoomReservationDto roomReservationDto = new RoomReservationDto(
        reservationId,
        startDate,
        endDate,
        reservedAt,
        ReservationStatus.ACTIVE,
        "STANDARD"
    );
    RoomRateDto roomRateDto = new RoomRateDto(BigDecimal.valueOf(100));
    PaymentInformationDto paymentInformationDto = new PaymentInformationDto(UUID.randomUUID().toString(), reservationId, cardDto);
    Mockito.when(reservationProvider.provide(reservationId)).thenReturn(roomReservationDto);
    Mockito.when(rateProvider.findRateAt("STANDARD", startDate, endDate, reservedAt)).thenReturn(roomRateDto);

    // when:
    chargeCardUseCase.charge(paymentInformationDto, 100);
    chargeCardUseCase.charge(paymentInformationDto, 100);

    // then:
    Mockito.verify(externalCardService, Mockito.times(1)).charge(Mockito.eq(owner), Mockito.eq(cardNr), Mockito.eq(validityDate), Mockito.any());
  }

}