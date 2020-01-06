package com.github.tomek39856.hotel.manager.itops;

import com.github.tomek39856.hotel.manager.rate.provider.RateProvider;
import com.github.tomek39856.hotel.manager.reservation.provider.ReservationProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {RateProvider.class, ReservationProvider.class})
@EnableEurekaClient
public class ItopsApplication {
  public static void main(String[] args) {
    SpringApplication.run(ItopsApplication.class, args);
  }
}
