package com.github.tomek39856.hotel.manager.reservation.infrastructure;

import com.github.tomek39856.hotel.manager.reservation.event.out.NoShowEvent;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class EventPublisher {
  private final JmsTemplate jmsTemplate;
  private final Topic reservationNoShowTopic;

  public EventPublisher(JmsTemplate jmsTemplate, Topic reservationNoShowTopic) {
    this.jmsTemplate = jmsTemplate;
    this.reservationNoShowTopic = reservationNoShowTopic;
  }

  public void publishEvent(NoShowEvent event) {
    jmsTemplate.convertAndSend(reservationNoShowTopic, event);
  }
}
