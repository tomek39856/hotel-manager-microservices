package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.dto.CreatePaymentDto;
import com.github.tomek39856.hotel.manager.payment.event.out.itops.ChargeCancellationFeeEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ChargeCancellationFeeForReservationUseCaseTest extends EventPublishingComponentTest {
  @Autowired
  PaymentRepository paymentRepository;
  @Autowired
  ChargeCancellationFeeForReservationUseCase chargeCancellationFeeForReservationUseCase;

  @BeforeEach
  void setUp() {
    paymentRepository.clear();
  }

  @Test
  @DisplayName("should send charge cancellation event")
  void shouldSendChargeCancellationEvent() {
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

    // when:
    chargeCancellationFeeForReservationUseCase.execute(paymentInformation.getReservationId());

    // then:
    Mockito.verify(eventPublisher).publishEvent(publishEventCaptor.capture());
    assertTrue(publishEventCaptor.getValue() instanceof ChargeCancellationFeeEvent);
    assertEquals(paymentInformation.getReservationId(), ((ChargeCancellationFeeEvent) publishEventCaptor.getValue()).getPayment().getReservationId());
  }

  @Test
  @DisplayName("should throw when payment not found")
  void shouldThrowWhenPaymentNotFound() {
    // given:
    String wrongId = UUID.randomUUID().toString();

    // expect:
    assertThrows(NotFoundException.class, () -> chargeCancellationFeeForReservationUseCase.execute(wrongId));
  }
}