package org.example.minitest1.repository;

import org.example.minitest1.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    boolean existsByCode(String code);


}
