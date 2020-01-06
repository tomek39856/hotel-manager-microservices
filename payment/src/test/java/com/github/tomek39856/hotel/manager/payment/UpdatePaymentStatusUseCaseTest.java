package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.dto.CreatePaymentDto;
import com.github.tomek39856.hotel.manager.payment.event.out.ReservationHoldEstablishedEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.ReservationPaymentFailedEvent;
import com.github.tomek39856.hotel.manager.payment.infrastructure.Event;
import com.github.tomek39856.hotel.manager.payment.infrastructure.EventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpdatePaymentStatusUseCaseTest extends ComponentTest {
  @Autowired
  PaymentRepository paymentRepository;
  @Autowired
  UpdatePaymentStatusUseCase useCase;
  @MockBean
  EventPublisher eventPublisher;
  @Captor
  protected ArgumentCaptor<Event> publishEventCaptor;

  @BeforeEach
  void setUp() {
    paymentRepository.clear();
  }

  @Test
  @DisplayName("should mark payment as charged")
  void shouldMarkPaymentAsCharged() {
    // given:
    PaymentInformation paymentInformation = PaymentInformation.ofDto(
        new CreatePaymentDto(
            UUID.randomUUID().toString(),
            "Adam Nowak",
            "123456",
            LocalDate.now()
        )
    );
    paymentRepository.save(paymentInformation);

    // when:
    useCase.chargeSuccess(paymentInformation.getId());

    // then:
    assertEquals(PaymentStatus.CHARGED, paymentRepository.findOneById(paymentInformation.getId()).get().getPaymentStatus());
  }

  @Test
  @DisplayName("should mark payment as failed when charge failed and send event")
  void shouldMarkPaymentAsFailedWhenChargeFailedAndSendEvent() {
    // given:
    PaymentInformation paymentInformation = PaymentInformation.ofDto(
        new CreatePaymentDto(
            UUID.randomUUID().toString(),
            "Adam Nowak",
            "123456",
            LocalDate.now()
        )
    );
    paymentRepository.save(paymentInformation);

    // when:
    useCase.chargeFailed(paymentInformation.getId());

    // then:
    assertEquals(PaymentStatus.FAILED, paymentRepository.findOneById(paymentInformation.getId()).get().getPaymentStatus());
    Mockito.verify(eventPublisher).publishEvent(publishEventCaptor.capture());
    assertTrue(publishEventCaptor.getValue() instanceof ReservationPaymentFailedEvent);
    assertEquals(paymentInformation.getReservationId(), ((ReservationPaymentFailedEvent) publishEventCaptor.getValue()).getReservationId());
  }

  @Test
  @DisplayName("should mark payment as hold succeed and send event")
  void shouldMarkPaymentAsHoldSucceedAndSendEvent() {
    // given:
    PaymentInformation paymentInformation = PaymentInformation.ofDto(
        new CreatePaymentDto(
            UUID.randomUUID().toString(),
            "Adam Nowak",
            "123456",
            LocalDate.now()
        )
    );
    paymentRepository.save(paymentInformation);

    // when:
    useCase.holdSuccess(paymentInformation.getId());

    // then:
    assertEquals(PaymentStatus.HOLD, paymentRepository.findOneById(paymentInformation.getId()).get().getPaymentStatus());
    Mockito.verify(eventPublisher).publishEvent(publishEventCaptor.capture());
    assertTrue(publishEventCaptor.getValue() instanceof ReservationHoldEstablishedEvent);
    assertEquals(paymentInformation.getReservationId(), ((ReservationHoldEstablishedEvent) publishEventCaptor.getValue()).getReservationId());
  }

  @Test
  @DisplayName("should mark payment as failed when hold failed and send event")
  void shouldMarkPaymentAsFailedWhenHoldFailedAndSendEvent() {
    // given:
    PaymentInformation paymentInformation = PaymentInformation.ofDto(
        new CreatePaymentDto(
            UUID.randomUUID().toString(),
            "Adam Nowak",
            "123456",
            LocalDate.now()
        )
    );
    paymentRepository.save(paymentInformation);

    // when:
    useCase.holdFailed(paymentInformation.getId());

    // then:
    assertEquals(PaymentStatus.FAILED, paymentRepository.findOneById(paymentInformation.getId()).get().getPaymentStatus());
    Mockito.verify(eventPublisher).publishEvent(publishEventCaptor.capture());
    assertTrue(publishEventCaptor.getValue() instanceof ReservationPaymentFailedEvent);
    assertEquals(paymentInformation.getReservationId(), ((ReservationPaymentFailedEvent) publishEventCaptor.getValue()).getReservationId());
  }
}