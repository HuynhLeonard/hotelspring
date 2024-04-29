package com.leonard.demohotel.Repository;

import com.leonard.demohotel.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select distinct r.roomType from Room r")    // goi tu model, chu khong phai tu database
    List<String> findDistinctRoomType();
}
