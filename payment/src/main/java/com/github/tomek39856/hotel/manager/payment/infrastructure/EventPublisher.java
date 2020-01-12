package com.github.tomek39856.hotel.manager.payment.infrastructure;

import com.github.tomek39856.hotel.manager.payment.event.out.ReservationHoldEstablishedEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.ReservationPaymentFailedEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.itops.ChargeCancellationFeeEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.itops.ChargeCardEvent;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class EventPublisher {
  private final Topic paymentStatusTopic;
  private final Topic paymentCardChargeTopic;
  private final JmsTemplate jmsTemplate;

  public EventPublisher(Topic paymentStatusTopic, Topic paymentCardChargeTopic, JmsTemplate jmsTemplate) {
    this.paymentStatusTopic = paymentStatusTopic;
    this.paymentCardChargeTopic = paymentCardChargeTopic;
    this.jmsTemplate = jmsTemplate;
  }

  public void publishEvent(ReservationHoldEstablishedEvent event) {
    System.out.println("publishing event " + event.getClass().getName());
    jmsTemplate.convertAndSend(paymentStatusTopic, event);
  }

  public void publishEvent(ReservationPaymentFailedEvent event) {
    System.out.println("publishing event " + event.getClass().getName());
    jmsTemplate.convertAndSend(paymentStatusTopic, event);
  }

  public void publishEvent(ChargeCardEvent event) {
    System.out.println("publishing event " + event.getClass().getName());
    jmsTemplate.convertAndSend(paymentCardChargeTopic, event);
  }
}
