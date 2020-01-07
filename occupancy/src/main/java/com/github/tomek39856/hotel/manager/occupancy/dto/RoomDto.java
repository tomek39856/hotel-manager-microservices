package com.github.tomek39856.hotel.manager.occupancy.dto;

import com.github.tomek39856.hotel.manager.occupancy.RoomType;

public class RoomDto {
  private final String roomId;
  private final RoomType roomType;
  private final boolean parkView;
  private final boolean shower;
  private final boolean bath;

  public RoomDto(String roomId, RoomType roomType, boolean parkView, boolean shower, boolean bath) {
    this.roomId = roomId;
    this.roomType = roomType;
    this.parkView = parkView;
    this.shower = shower;
    this.bath = bath;
  }
}
