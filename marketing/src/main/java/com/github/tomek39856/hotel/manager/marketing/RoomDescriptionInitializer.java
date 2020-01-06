package com.github.tomek39856.hotel.manager.marketing;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
class RoomDescriptionInitializer implements InitializingBean {
  private final RoomDescriptionRepository roomDescriptionRepository;

  RoomDescriptionInitializer(RoomDescriptionRepository roomDescriptionRepository) {
    this.roomDescriptionRepository = roomDescriptionRepository;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    roomDescriptionRepository.save(new RoomDescription(RoomType.KING, "king room", "image1.jpg"));
    roomDescriptionRepository.save(new RoomDescription(RoomType.QUEEN, "queen room", "image2.jpg"));
    roomDescriptionRepository.save(new RoomDescription(RoomType.STANDARD, "standard room", "image3.jpg"));
  }
}
