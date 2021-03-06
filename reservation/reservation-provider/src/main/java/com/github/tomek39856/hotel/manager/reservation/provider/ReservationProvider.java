package com.github.tomek39856.hotel.manager.reservation.provider;

import com.github.tomek39856.hotel.manager.reservation.provider.dto.RoomReservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("reservation")
public interface ReservationProvider {
  @RequestMapping("/itops/reservation/{id}")
  RoomReservation provide(@PathVariable String id);
}
