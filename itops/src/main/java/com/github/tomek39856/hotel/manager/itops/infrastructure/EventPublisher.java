package com.github.tomek39856.hotel.manager.itops.infrastructure;

import com.github.tomek39856.hotel.manager.itops.event.out.CardChargedEvent;
import com.github.tomek39856.hotel.manager.itops.event.out.ChargeCardFailedEvent;
import com.github.tomek39856.hotel.manager.itops.event.out.HoldCreatedEvent;
import com.github.tomek39856.hotel.manager.itops.event.out.HoldFailedEvent;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class EventPublisher {
  private final JmsTemplate jmsTemplate;
  private final Topic cardChargeTopic;
  private final Topic cardHoldTopic;

  public EventPublisher(JmsTemplate jmsTemplate, Topic cardChargeTopic, Topic cardHoldTopic) {
    this.jmsTemplate = jmsTemplate;
    this.cardChargeTopic = cardChargeTopic;
    this.cardHoldTopic = cardHoldTopic;
  }

  public void publishEvent(CardChargedEvent event) {
    jmsTemplate.convertAndSend(cardChargeTopic, event);
  }

  public void publishEvent(ChargeCardFailedEvent event) {
    jmsTemplate.convertAndSend(cardChargeTopic, event);
  }

  public void publishEvent(HoldCreatedEvent event) {
    jmsTemplate.convertAndSend(cardHoldTopic, event);
  }

  public void publishEvent(HoldFailedEvent event) {
    jmsTemplate.convertAndSend(cardHoldTopic, event);
  }
}
