package com.github.tomek39856.hotel.manager.marketing;

import com.github.tomek39856.hotel.manager.marketing.dto.RoomDescriptionDto;

import java.util.UUID;

class RoomDescription {
  private final String id = UUID.randomUUID().toString();
  private final RoomType roomType;
  private final String description;
  private final String imageUrl;

  RoomDescription(RoomType roomType, String description, String imageUrl) {
    this.roomType = roomType;
    this.description = description;
    this.imageUrl = imageUrl;
  }

  RoomType getRoomType() {
    return roomType;
  }

  RoomDescriptionDto toApi() {
    return new RoomDescriptionDto(description, imageUrl);
  }
}
