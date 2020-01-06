package com.github.tomek39856.hotel.manager.itops.config;

import com.github.tomek39856.hotel.manager.itops.ExternalCardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
class CardServiceConfiguration {
  @Bean
  @Profile("!prod")
  ExternalCardService externalCardService() {
    return new ExternalCardService() {
      @Override
      public void charge(String owner, String cardNumber, LocalDate validityDate, BigDecimal amount) {
        if (validityDate.isBefore(LocalDate.now())) {
          throw new RuntimeException("payment failed");
        }
        System.out.println("card " + cardNumber + " charged with: " + amount + " ");
      }

      @Override
      public void hold(String owner, String cardNumber, LocalDate validityDate, BigDecimal amount) {
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (cardNumber.startsWith("55")) {
          throw new RuntimeException("payment failed");
        }
      }
    };
  }
}
