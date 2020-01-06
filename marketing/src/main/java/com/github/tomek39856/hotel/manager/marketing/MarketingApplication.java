package com.github.tomek39856.hotel.manager.marketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MarketingApplication {
  public static void main(String[] args) {
    SpringApplication.run(MarketingApplication.class, args);
  }
}
