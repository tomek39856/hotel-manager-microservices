package com.github.tomek39856.hotel.manager.guest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GuestApplication {
  public static void main(String[] args) {
    SpringApplication.run(GuestApplication.class, args);
  }
}
