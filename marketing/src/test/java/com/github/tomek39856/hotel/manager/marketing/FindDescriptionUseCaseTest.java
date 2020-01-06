package com.github.tomek39856.hotel.manager.marketing;

import com.github.tomek39856.hotel.manager.marketing.dto.RoomDescriptionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FindDescriptionUseCaseTest extends ComponentTest {
  @Autowired
  RoomDescriptionRepository repository;
  @Autowired
  FindDescriptionUseCase useCase;

  @BeforeEach
  void setUp() {
    repository.clear();
  }

  @Test
  @DisplayName("should find description for room type")
  void shouldFindDescriptionForRoomType() {
    // given:
    repository.save(new RoomDescription(RoomType.KING, "test", "img1.jpg"));

    // when:
    RoomDescriptionDto description = useCase.findDescription(RoomType.KING);

    // then:
    assertEquals("test", description.getDescription());
    assertEquals("img1.jpg", description.getImageUrl());
  }

  @Test
  @DisplayName("should throw when no description for room type")
  void shouldThrowWhenNoDescForRoom() {
    // expect:
    assertThrows(NotFoundException.class, () -> useCase.findDescription(RoomType.KING));
  }
}