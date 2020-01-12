package com.github.tomek39856.hotel.manager.payment.infrastructure;

import com.github.tomek39856.hotel.manager.payment.event.out.ReservationHoldEstablishedEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.ReservationPaymentFailedEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.itops.ChargeCardEvent;
import com.github.tomek39856.hotel.manager.payment.event.out.itops.CreateHoldEvent;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class EventPublisher {
  private final Topic paymentStatusTopic;
  private final Topic paymentCardChargeTopic;
  private final Topic paymentCardHoldTopic;

  private final JmsTemplate jmsTemplate;

  public EventPublisher(Topic paymentStatusTopic, Topic paymentCardChargeTopic, Topic paymentCardHoldTopic, JmsTemplate jmsTemplate) {
    this.paymentStatusTopic = paymentStatusTopic;
    this.paymentCardChargeTopic = paymentCardChargeTopic;
    this.paymentCardHoldTopic = paymentCardHoldTopic;
    this.jmsTemplate = jmsTemplate;
  }

  public void publishEvent(ReservationHoldEstablishedEvent event) {
    jmsTemplate.convertAndSend(paymentStatusTopic, event);
  }

  public void publishEvent(ReservationPaymentFailedEvent event) {
    jmsTemplate.convertAndSend(paymentStatusTopic, event);
  }

  public void publishEvent(ChargeCardEvent event) {
    jmsTemplate.convertAndSend(paymentCardChargeTopic, event);
  }

  public void publishEvent(CreateHoldEvent event) {
    jmsTemplate.convertAndSend(paymentCardHoldTopic, event);
  }
}
