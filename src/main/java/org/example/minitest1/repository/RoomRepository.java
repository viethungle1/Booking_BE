package org.example.minitest1.repository;

import org.example.minitest1.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByCode(String code);

    @Modifying
    @Transactional
    @Query("UPDATE Room r SET r.roomType = null WHERE r.roomType.id = :roomTypeId")
    void setRoomTypeToNull(@Param("roomTypeId") Long roomTypeId);

}