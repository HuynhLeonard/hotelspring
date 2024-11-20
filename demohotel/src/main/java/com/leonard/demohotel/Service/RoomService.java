package com.leonard.demohotel.Service;

import com.leonard.demohotel.Model.Room;
import com.leonard.demohotel.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.swing.tree.RowMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;
    @Override
    public Room addNewRoom(MultipartFile photo, String roomType, BigDecimal RoomPrice) throws SQLException, IOException {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(RoomPrice);
        if(!photo.isEmpty()) {
            byte[] photoBytes = photo.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }
        return roomRepository.save(room);
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();    // auto generate by JPA framework
    }

    @Override
    public byte[] getPhotoByRoomID(Long roomID) throws SQLException {
        Optional<Room> theRoom = roomRepository.findById(roomID);
        if(theRoom.isEmpty()) {
            //throw new ResourceNotFoundException("Sorry, Room not found!");
        }
        Blob photoBlob = theRoom.get().getPhoto();
        if(photoBlob != null) {
            return photoBlob.getBytes(1, (int) photoBlob.length()); // get all the byte in the picture
        }
        return null;
    }

    @Override
    public void deleteRoom(Long roomID) {
        Optional<Room> theRoom = roomRepository.findById(roomID);
        if(theRoom.isPresent()) {
            roomRepository.deleteById(roomID);
        }
    }

    @Override
    public Room updateRoom(Long roomID, String roomType, BigDecimal roomPrice, byte[] photoBytes) {
        return null;
    }

    @Override
    public Optional<Room> getRoomByID(Long roomID) {
        return Optional.of(roomRepository.findById(roomID).get());
    }

    @Override
    public List<Room> getAvailableRoom(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
        return null;
    }
}
