package org.example.minitest1.service;

import org.example.minitest1.dto.request.room.RoomSaveRequest;
import org.example.minitest1.model.Room;

public interface IRoomService extends GeneralService<Room>{
    Room create(RoomSaveRequest roomSaveRequest);

    Room update(Long id, RoomSaveRequest roomSaveRequest);
}
