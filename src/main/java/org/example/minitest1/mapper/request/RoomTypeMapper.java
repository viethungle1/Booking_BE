package org.example.minitest1.mapper.request;

import org.example.minitest1.dto.request.roomtype.RoomTypeSaveRequest;
import org.example.minitest1.mapper.IMapper;
import org.example.minitest1.model.RoomType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomTypeMapper extends IMapper<RoomType, RoomTypeSaveRequest> {
}

