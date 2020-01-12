package com.github.tomek39856.hotel.manager.payment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomek39856.hotel.manager.payment.event.in.NoShowEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.ReservationHoldEstablishedEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.ReservationPaymentFailedEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.itops.ChargeCancellationFeeEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.itops.ChargeCardWithFullPriceEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.itops.CreateHoldEvent;
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
  public Topic paymentStatusTopic() {
    return new ActiveMQTopic("payment.status");
  }

  @Bean
  public Topic paymentCardChargeTopic() {
    return new ActiveMQTopic("payment.card.charge");
  }

  @Bean
  public Topic paymentCardHoldTopic() {
    return new ActiveMQTopic("payment.card.hold");
  }

  @Bean
  public MessageConverter messageConverter(ObjectMapper objectMapper){
    MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
    messageConverter.setObjectMapper(objectMapper);
    messageConverter.setTargetType(MessageType.TEXT);


    messageConverter.setTypeIdPropertyName("type");

    HashMap<String, Class<?>> idMapping = new HashMap<>();

    // in:
    idMapping.put(NoShowEvent.class.getSimpleName(), NoShowEvent.class);

    // out:
    idMapping.put(ReservationHoldEstablishedEvent.class.getSimpleName(), ReservationHoldEstablishedEvent.class);
    idMapping.put(ReservationPaymentFailedEvent.class.getSimpleName(), ReservationPaymentFailedEvent.class);
    idMapping.put(ChargeCancellationFeeEvent.class.getSimpleName(), ChargeCancellationFeeEvent.class);
    idMapping.put(ChargeCardWithFullPriceEvent.class.getSimpleName(), ChargeCardWithFullPriceEvent.class);
    idMapping.put(CreateHoldEvent.class.getSimpleName(), CreateHoldEvent.class);

    messageConverter.setTypeIdMappings(idMapping);
    return messageConverter;
  }

}
