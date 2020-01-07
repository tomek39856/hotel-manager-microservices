package com.github.tomek39856.hotel.manager.occupancy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@Profile("local")
class RoomInitializer implements InitializingBean {
  private final RoomRepository roomRepository;

  RoomInitializer(RoomRepository roomRepository) {
    this.roomRepository = roomRepository;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    IntStream.range(0, 100).forEach(value ->
        roomRepository.save(new Room(RoomType.QUEEN, true, true, false))
    );
    IntStream.range(0, 100).forEach(value ->
      roomRepository.save(new Room(RoomType.STANDARD, true, false, true))
    );
    roomRepository.save(new Room(RoomType.KING, true, true, true));
  }
}
