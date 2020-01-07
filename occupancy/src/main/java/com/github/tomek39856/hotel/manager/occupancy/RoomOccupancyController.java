package com.github.tomek39856.hotel.manager.occupancy;

import com.github.tomek39856.hotel.manager.occupancy.dto.CheckInDto;
import com.github.tomek39856.hotel.manager.occupancy.dto.RoomDto;
import com.github.tomek39856.hotel.manager.occupancy.dto.SearchParametersDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomOccupancyController {
  private final FindUnoccupiedRoomsUseCase findUnoccupiedRoomsUseCase;
  private final CheckInUseCase checkInUseCase;

  public RoomOccupancyController(FindUnoccupiedRoomsUseCase findUnoccupiedRoomsUseCase, CheckInUseCase checkInUseCase) {
    this.findUnoccupiedRoomsUseCase = findUnoccupiedRoomsUseCase;
    this.checkInUseCase = checkInUseCase;
  }

  @PostMapping("/check-in")
  public void checkIn(@RequestBody CheckInDto checkInDto) {
    checkInUseCase.execute(checkInDto);
  }

  @GetMapping("/not-occupied")
  public List<RoomDto> getNotOccupiedRooms(SearchParametersDto searchParametersDto) {
    return findUnoccupiedRoomsUseCase.execute(searchParametersDto);
  }
}
