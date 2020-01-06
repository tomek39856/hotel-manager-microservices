package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.infrastructure.EventPublisher;
import com.github.tomek39856.hotel.manager.payment.infrastructure.UseCase;

@UseCase
class UpdatePaymentStatusUseCase {
  private final PaymentRepository paymentRepository;
  private final EventPublisher eventPublisher;

  UpdatePaymentStatusUseCase(PaymentRepository paymentRepository, EventPublisher eventPublisher) {
    this.paymentRepository = paymentRepository;
    this.eventPublisher = eventPublisher;
  }

  void chargeSuccess(String paymentId) {
    PaymentInformation paymentInformation = paymentRepository.findOneById(paymentId).orElseThrow(NotFoundException::new);
    paymentInformation.chargeSuccess();
  }

  void chargeFailed(String paymentId) {
    PaymentInformation paymentInformation = paymentRepository.findOneById(paymentId).orElseThrow(NotFoundException::new);
    paymentInformation.chargeFailed(eventPublisher);
  }

  void holdSuccess(String paymentId) {
    PaymentInformation paymentInformation = paymentRepository.findOneById(paymentId).orElseThrow(NotFoundException::new);
    paymentInformation.holdSuccess(eventPublisher);
  }

  void holdFailed(String paymentId) {
    PaymentInformation paymentInformation = paymentRepository.findOneById(paymentId).orElseThrow(NotFoundException::new);
    paymentInformation.holdFailed(eventPublisher);
  }
}
