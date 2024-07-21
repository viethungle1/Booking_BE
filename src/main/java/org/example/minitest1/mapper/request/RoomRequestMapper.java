package org.example.minitest1.mapper.request;

import org.example.minitest1.dto.request.room.RoomSaveRequest;
import org.example.minitest1.mapper.IMapper;
import org.example.minitest1.model.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomRequestMapper extends IMapper<Room, RoomSaveRequest> {
}
