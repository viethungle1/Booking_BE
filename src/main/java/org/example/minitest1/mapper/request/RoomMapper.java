package org.example.minitest1.mapper.request;

import org.example.minitest1.dto.request.room.RoomSaveRequest;
import org.example.minitest1.mapper.IMapper;
import org.example.minitest1.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoomMapper extends IMapper<Room, RoomSaveRequest> {
    void updateRoom(RoomSaveRequest dto, @MappingTarget Room entity);
}
