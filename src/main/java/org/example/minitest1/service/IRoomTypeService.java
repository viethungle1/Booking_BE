package org.example.minitest1.service;


import org.example.minitest1.dto.request.roomtype.RoomTypeSaveRequest;
import org.example.minitest1.model.RoomType;

public interface IRoomTypeService extends GeneralService<RoomType>{
    RoomType create(RoomTypeSaveRequest roomTypeSaveRequest);

    RoomType update(Long id, RoomTypeSaveRequest roomTypeSaveRequest);
}
