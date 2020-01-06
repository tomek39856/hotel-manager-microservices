package com.github.tomek39856.hotel.manager.marketing;

import com.github.tomek39856.hotel.manager.marketing.dto.RoomDescriptionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/description")
class RoomDescriptionController {
  private final FindDescriptionUseCase findDescriptionUseCase;

  RoomDescriptionController(FindDescriptionUseCase findDescriptionUseCase) {
    this.findDescriptionUseCase = findDescriptionUseCase;
  }

  @GetMapping
  RoomDescriptionDto findDescription(RoomType roomType) {
    return findDescriptionUseCase.findDescription(roomType);
  }
}
