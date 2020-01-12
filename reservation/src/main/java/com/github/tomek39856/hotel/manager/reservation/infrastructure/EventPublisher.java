package com.github.tomek39856.hotel.manager.reservation.infrastructure;

import com.github.tomek39856.hotel.manager.reservation.event.out.NoShowEvent;
import com.github.tomek39856.hotel.manager.reservation.event.out.TestEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class EventPublisher {
  private final ApplicationEventPublisher applicationEventPublisher;
  private final JmsTemplate jmsTemplate;
  private final Topic reservationTopic;
  private final Topic reservationNoShowTopic;

  public EventPublisher(ApplicationEventPublisher applicationEventPublisher, JmsTemplate jmsTemplate, Topic reservationTopic, Topic reservationNoShowTopic) {
    this.applicationEventPublisher = applicationEventPublisher;
    this.jmsTemplate = jmsTemplate;
    this.reservationTopic = reservationTopic;
    this.reservationNoShowTopic = reservationNoShowTopic;
  }

  public void publishEvent(Event event) {
    System.out.println("publishing event " + event.getClass().getName());
    applicationEventPublisher.publishEvent(event);
    jmsTemplate.convertAndSend(reservationTopic, "test");
  }

  public void publishEvent(NoShowEvent event) {
    System.out.println("publishing event " + event.getClass().getName());
    applicationEventPublisher.publishEvent(event);
    jmsTemplate.convertAndSend(reservationNoShowTopic, event);
  }

  public void publishEvent(TestEvent event) {
    System.out.println("publishing event " + event.getClass().getName());
    applicationEventPublisher.publishEvent(event);
    jmsTemplate.convertAndSend(reservationNoShowTopic, event);
  }
}
