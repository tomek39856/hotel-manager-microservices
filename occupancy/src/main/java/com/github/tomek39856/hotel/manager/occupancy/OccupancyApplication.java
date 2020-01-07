package com.github.tomek39856.hotel.manager.occupancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OccupancyApplication {
  public static void main(String[] args) {
    SpringApplication.run(OccupancyApplication.class, args);
  }
}
