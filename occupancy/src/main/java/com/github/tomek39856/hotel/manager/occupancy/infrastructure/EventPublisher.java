package com.github.tomek39856.hotel.manager.occupancy.infrastructure;

import com.github.tomek39856.hotel.manager.occupancy.event.out.UserCheckedInEvent;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class EventPublisher {
  private final JmsTemplate jmsTemplate;
  private final Topic occupancyCheckInTopic;

  public EventPublisher(JmsTemplate jmsTemplate, Topic occupancyCheckInTopic) {
    this.jmsTemplate = jmsTemplate;
    this.occupancyCheckInTopic = occupancyCheckInTopic;
  }

  public void publishEvent(UserCheckedInEvent event) {
    jmsTemplate.convertAndSend(occupancyCheckInTopic, event);
  }
}
