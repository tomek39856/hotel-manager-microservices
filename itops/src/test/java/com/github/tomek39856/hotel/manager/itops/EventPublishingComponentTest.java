package com.github.tomek39856.hotel.manager.itops;

import com.github.tomek39856.hotel.manager.itops.infrastructure.Event;
import com.github.tomek39856.hotel.manager.itops.infrastructure.EventPublisher;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.mock.mockito.MockBean;

public class EventPublishingComponentTest extends ComponentTest {
  @MockBean
  protected EventPublisher eventPublisher;
  @Captor
  protected ArgumentCaptor<Event> publishEventCaptor;
}
