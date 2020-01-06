package com.github.tomek39856.hotel.manager.bus.config;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;

@Configuration
public class ActiveMqConfig {
  @Bean
  public BrokerService createBrokerService() throws Exception {
    BrokerService broker = new BrokerService();
    TransportConnector connector = new TransportConnector();
    connector.setUri(new URI("tcp://localhost:61616"));
    broker.addConnector(connector);
    return broker;
  }
}
