package org.example.minitest1.repository;

import org.example.minitest1.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Reservation r SET r.room = null WHERE r.room.id = :roomId")
    void setRoomToNull(@Param("roomId") Long roomId);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Reservation r " +
            "WHERE r.room.id = :roomId AND " +
            "(:start BETWEEN r.createdDate AND r.endDate OR " +
            ":end BETWEEN r.createdDate AND r.endDate)")
    boolean existsConflictingReservations(
            @Param("roomId") Long roomId,
            @Param("start") LocalDateTime startTime,
            @Param("end") LocalDateTime endDate
    );
}
