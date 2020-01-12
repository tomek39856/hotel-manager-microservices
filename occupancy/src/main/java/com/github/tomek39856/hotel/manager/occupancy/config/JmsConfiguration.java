package com.github.tomek39856.hotel.manager.occupancy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomek39856.hotel.manager.occupancy.event.in.ReservationPaymentFailedEvent;
import com.github.tomek39856.hotel.manager.occupancy.event.out.UserCheckedInEvent;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.Topic;
import java.util.HashMap;

@Configuration
@EnableJms
public class JmsConfiguration {
  @Bean
  public Topic occupancyCheckInTopic() {
    return new ActiveMQTopic("occupancy.checkIn");
  }

  @Bean
  public MessageConverter messageConverter(ObjectMapper objectMapper){
    MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
    messageConverter.setObjectMapper(objectMapper);
    messageConverter.setTargetType(MessageType.TEXT);
    messageConverter.setTypeIdPropertyName("type");

    HashMap<String, Class<?>> idMapping = new HashMap<>();

    // in:
    idMapping.put(ReservationPaymentFailedEvent.class.getSimpleName(), ReservationPaymentFailedEvent.class);

    // out:
    idMapping.put(UserCheckedInEvent.class.getSimpleName(), UserCheckedInEvent.class);


    messageConverter.setTypeIdMappings(idMapping);
    return messageConverter;
  }

}
