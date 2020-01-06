package com.github.tomek39856.hotel.manager.payment;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
class PaymentRepository {
  private final Map<String, PaymentInformation> payments = new ConcurrentHashMap<>();

  void save(PaymentInformation paymentInformation) {
    payments.put(paymentInformation.getId(), paymentInformation);
  }

  Optional<PaymentInformation> findOneByReservationId(String reservationId) {
    return payments.values().stream()
        .filter(payment -> reservationId.equals(payment.getReservationId()))
        .findAny();
  }

  Optional<PaymentInformation> findOneById(String paymentId) {
    return Optional.ofNullable(payments.get(paymentId));
  }

  void clear() {
    this.payments.clear();
  }

}
