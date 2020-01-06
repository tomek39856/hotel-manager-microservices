package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.dto.CreatePaymentDto;
import com.github.tomek39856.hotel.manager.payment.dto.PaymentInformationDto;
import com.github.tomek39856.hotel.manager.payment.event.out.itops.CreateHoldEvent;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreatePaymentUseCaseTest extends ComponentTest {
  @Autowired
  PaymentRepository paymentRepository;
  @Autowired
  CreatePaymentUseCase createPaymentUseCase;
  @MockBean
  EventPublisher eventPublisher;
  @Captor
  protected ArgumentCaptor<Event> publishEventCaptor;

  @BeforeEach
  void setUp() {
    paymentRepository.clear();
  }

  @Test
  @DisplayName("should create new payment and send event when not existed for given reservation before")
  void shouldCreateNewPaymentWhenNotExistedBefore() {
    // given:
    String reservationId = UUID.randomUUID().toString();
    CreatePaymentDto createPaymentDto = new CreatePaymentDto(
        reservationId,
        "Adam Nowak",
        "123456",
        LocalDate.now()
    );

    // when:
    createPaymentUseCase.execute(createPaymentDto);

    // then:
    Optional<PaymentInformation> payment = paymentRepository.findOneByReservationId(reservationId);
    assertTrue(payment.isPresent());
    Mockito.verify(eventPublisher).publishEvent(publishEventCaptor.capture());
    assertTrue(publishEventCaptor.getValue() instanceof CreateHoldEvent);
    PaymentInformationDto eventPayload = ((CreateHoldEvent) publishEventCaptor.getValue()).getPayment();
    assertEquals(reservationId, eventPayload.getReservationId());
    assertEquals(payment.get().getId(), eventPayload.getId());
  }

  @Test
  @DisplayName("should ignore new payment when existed for given reservation before")
  void shouldIgnoreNewPaymentWhenExistedBefore() {
    // given:
    String reservationId = UUID.randomUUID().toString();
    PaymentInformation paymentInformation = PaymentInformation.ofDto(
        new CreatePaymentDto(
            reservationId,
            "Adam Nowak",
            "123456",
            LocalDate.now()
        )
    );
    paymentRepository.save(paymentInformation);
    CreatePaymentDto createPaymentDto = new CreatePaymentDto(
        reservationId,
        "New Name",
        "654321",
        LocalDate.now()
    );

    // when:
    createPaymentUseCase.execute(createPaymentDto);

    // then:
    Optional<PaymentInformation> payment = paymentRepository.findOneByReservationId(reservationId);
    assertTrue(payment.isPresent());
    assertEquals("Adam Nowak", payment.get().getCard().toDto().getOwner());
    Mockito.verifyNoMoreInteractions(eventPublisher);
  }
}