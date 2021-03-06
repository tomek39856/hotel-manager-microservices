package com.github.tomek39856.hotel.manager.reservation.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomek39856.hotel.manager.reservation.event.out.NoShowEvent;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import javax.jms.Topic;
import java.util.HashMap;

@Configuration
@EnableJms
public class JmsConfiguration {
  @Bean
  public Topic reservationNoShowTopic() {
    return new ActiveMQTopic("reservation.noShow");
  }

  @Bean
  public MessageConverter messageConverter(ObjectMapper objectMapper){
    MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
    messageConverter.setObjectMapper(objectMapper);
    messageConverter.setTargetType(MessageType.TEXT);
    messageConverter.setTypeIdPropertyName("type");

    HashMap<String, Class<?>> idMapping = new HashMap<>();
    idMapping.put(NoShowEvent.class.getSimpleName(), NoShowEvent.class);
    messageConverter.setTypeIdMappings(idMapping);

    return messageConverter;
  }
}
