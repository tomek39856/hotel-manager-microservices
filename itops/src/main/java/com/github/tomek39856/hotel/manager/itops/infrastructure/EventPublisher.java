package com.github.tomek39856.hotel.manager.itops.infrastructure;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
  private final ApplicationEventPublisher applicationEventPublisher;

  public EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public void publishEvent(Event event) {
    System.out.println("publishing event " + event.getClass().getName());
    applicationEventPublisher.publishEvent(event);
  }
}
