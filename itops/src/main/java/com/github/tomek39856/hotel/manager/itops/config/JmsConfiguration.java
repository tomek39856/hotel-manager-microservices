package com.github.tomek39856.hotel.manager.itops.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.github.tomek39856.hotel.manager.itops.event.in.ChargeCardEvent;
import com.github.tomek39856.hotel.manager.itops.event.in.CreateHoldEvent;
import com.github.tomek39856.hotel.manager.itops.event.out.CardChargedEvent;
import com.github.tomek39856.hotel.manager.itops.event.out.ChargeCardFailedEvent;
import com.github.tomek39856.hotel.manager.itops.event.out.HoldCreatedEvent;
import com.github.tomek39856.hotel.manager.itops.event.out.HoldFailedEvent;
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
  public Topic cardChargeTopic() {
    return new ActiveMQTopic("itops.card.charge");
  }

  @Bean
  public Topic cardHoldTopic() {
    return new ActiveMQTopic("itops.card.hold");
  }

  @Bean
  public MessageConverter messageConverter(ObjectMapper objectMapper){
    MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
    messageConverter.setObjectMapper(objectMapper);
    messageConverter.setTargetType(MessageType.TEXT);


    messageConverter.setTypeIdPropertyName("type");

    HashMap<String, Class<?>> idMapping = new HashMap<>();

    // in:
    idMapping.put(ChargeCardEvent.class.getSimpleName(), ChargeCardEvent.class);
    idMapping.put(CreateHoldEvent.class.getSimpleName(), CreateHoldEvent.class);

    // out:
    idMapping.put(CardChargedEvent.class.getSimpleName(), CardChargedEvent.class);
    idMapping.put(ChargeCardFailedEvent.class.getSimpleName(), ChargeCardFailedEvent.class);
    idMapping.put(HoldCreatedEvent.class.getSimpleName(), HoldCreatedEvent.class);
    idMapping.put(HoldFailedEvent.class.getSimpleName(), HoldFailedEvent.class);

    messageConverter.setTypeIdMappings(idMapping);
    return messageConverter;
  }

}
