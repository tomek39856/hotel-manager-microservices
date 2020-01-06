package com.github.tomek39856.hotel.manager.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceDiscoveryApplication {
  public static void main(String[] args) {
    SpringApplication.run(ServiceDiscoveryApplication.class, args);
  }
}
