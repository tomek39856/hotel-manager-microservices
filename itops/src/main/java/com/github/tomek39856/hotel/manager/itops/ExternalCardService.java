package com.github.tomek39856.hotel.manager.itops;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ExternalCardService {
  void charge(String owner, String cardNumber, LocalDate validityDate, BigDecimal amount);
  void hold(String owner, String cardNumber, LocalDate validityDate, BigDecimal amount);
}
