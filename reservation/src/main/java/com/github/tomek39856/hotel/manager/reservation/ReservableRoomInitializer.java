package com.github.tomek39856.hotel.manager.reservation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@Profile("local")
class ReservableRoomInitializer implements InitializingBean {
  private final ReservableRoomRepository reservableRoomRepository;

  ReservableRoomInitializer(ReservableRoomRepository reservableRoomRepository) {
    this.reservableRoomRepository = reservableRoomRepository;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    IntStream.range(0, 100).forEach(value ->
        reservableRoomRepository.save(new ReservableRoom(RoomType.QUEEN))
    );
    IntStream.range(0, 100).forEach(value ->
        reservableRoomRepository.save(new ReservableRoom(RoomType.STANDARD))
    );
    reservableRoomRepository.save(new ReservableRoom(RoomType.KING));
  }
}
