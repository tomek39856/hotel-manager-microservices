package com.github.tomek39856.hotel.manager.marketing.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RoomDescriptionDto {
  private final String description;
  private final String imageUrl;

  @JsonCreator
  public RoomDescriptionDto(String description, String imageUrl) {
    this.description = description;
    this.imageUrl = imageUrl;
  }

  public String getDescription() {
    return description;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}
