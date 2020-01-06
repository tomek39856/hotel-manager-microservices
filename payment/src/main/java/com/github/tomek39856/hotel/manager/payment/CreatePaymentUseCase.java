package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.dto.CreatePaymentDto;
import com.github.tomek39856.hotel.manager.payment.dto.PaymentInformationDto;
import com.github.tomek39856.hotel.manager.payment.infrastructure.EventPublisher;
import com.github.tomek39856.hotel.manager.payment.infrastructure.UseCase;
import com.github.tomek39856.hotel.manager.payment.event.out.itops.CreateHoldEvent;

import java.util.Optional;

@UseCase
class CreatePaymentUseCase {
  private final PaymentRepository paymentRepository;
  private final EventPublisher eventPublisher;

  CreatePaymentUseCase(PaymentRepository paymentRepository, EventPublisher eventPublisher) {
    this.paymentRepository = paymentRepository;
    this.eventPublisher = eventPublisher;
  }

  PaymentInformationDto execute(CreatePaymentDto createPaymentDto) {
    Optional<PaymentInformation> existingPayment = paymentRepository.findOneByReservationId(createPaymentDto.getReservationId());
    if (!existingPayment.isPresent()) {
      PaymentInformation paymentInformation = PaymentInformation.ofDto(createPaymentDto);
      paymentRepository.save(paymentInformation);
      eventPublisher.publishEvent(new CreateHoldEvent(paymentInformation.toDto()));
      return paymentInformation.toDto();
    } else {
      return existingPayment.get().toDto();
    }
  }
}
