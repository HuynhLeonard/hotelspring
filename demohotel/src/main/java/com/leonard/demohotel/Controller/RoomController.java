package com.leonard.demohotel.Controller;

import com.leonard.demohotel.Model.Room;
import com.leonard.demohotel.Response.RoomResponse;
import com.leonard.demohotel.Service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {
    private final IRoomService roomService;

    @PostMapping("/add/new-rooms")
    public ResponseEntity<RoomResponse> addNewRoom(
            @RequestParam("photo")MultipartFile photo,
            @RequestParam("roomType") String roomType,
            @RequestParam("roomPrice")BigDecimal roomPrice
            ) throws SQLException, IOException {

        Room savedRoom = roomService.addNewRoom(photo, roomType, roomPrice);
        RoomResponse response = new RoomResponse(savedRoom.getId(), savedRoom.getRoomType(), savedRoom.getRoomPrice());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/room-types")
    public List<String> getRoomTypes() {
        return roomService.getAllRoomTypes();
    }
}
