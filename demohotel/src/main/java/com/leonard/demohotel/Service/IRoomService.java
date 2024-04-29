package com.leonard.demohotel.Service;

import com.leonard.demohotel.Model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOError;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IRoomService {

    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal RoomPrice) throws SQLException, IOException;
    List<String> getAllRoomTypes();
    List<Room> getAllRoom();
    byte[] getPhotoByRoomID(Long roomID) throws SQLException;
    void deleteRoom(Long roomID);
    Room updateRoom(Long roomID, String roomType, BigDecimal roomPrice, byte[] photoBytes);
    Optional<Room> getRoomByID(Long roomID);
    List<Room> getAvailableRoom(LocalDate checkInDate, LocalDate checkOutDate, String roomType);
}
