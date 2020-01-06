package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.infrastructure.UseCase;

@UseCase
class CheckPaymentStatusUseCase {
  private final PaymentRepository paymentRepository;

  CheckPaymentStatusUseCase(PaymentRepository paymentRepository) {
    this.paymentRepository = paymentRepository;
  }

  PaymentStatus execute(String reservationId) {
    return paymentRepository.findOneByReservationId(reservationId)
        .map(PaymentInformation::getPaymentStatus)
        .orElseThrow(NotFoundException::new);
  }
}
