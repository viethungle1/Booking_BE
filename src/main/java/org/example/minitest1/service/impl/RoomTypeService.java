package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.mapper.request.RoomTypeMapper;
import org.example.minitest1.model.RoomType;
import org.example.minitest1.dto.request.roomtype.RoomTypeSaveRequest;
import org.example.minitest1.repository.RoomRepository;
import org.example.minitest1.repository.RoomTypeRepository;
import org.example.minitest1.service.IRoomTypeService;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeService implements IRoomTypeService {
    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    public List<RoomType> findAll() {
        return roomTypeRepository.findAll();
    }

    @Override
    public RoomType findById(Long id) {
        return roomTypeRepository.findById(id).orElse(null);
    }

    @Override
    public RoomType save(RoomType roomType) {
        return roomTypeRepository.save(roomType);
    }

    public boolean validationRoomTypeCode(RoomTypeSaveRequest roomTypeSaveRequest) {
        return roomTypeRepository.existsByCode(roomTypeSaveRequest.getCode());
    }

    @Override
    public RoomType create(RoomTypeSaveRequest roomTypeSaveRequest){
        boolean check = validationRoomTypeCode(roomTypeSaveRequest);
        if (check) {
            throw new RuntimeException("RoomType Code already exist");
        } else {
            RoomType roomType = roomTypeMapper.to(roomTypeSaveRequest);
            return roomTypeRepository.save(roomType);
        }
    }

    @Override
    public RoomType update(Long id, RoomTypeSaveRequest roomTypeSaveRequest) {
        RoomType roomTypeToUpdate = findById(id);
        if (!roomTypeSaveRequest.getCode().equals(roomTypeToUpdate.getCode()) && validationRoomTypeCode(roomTypeSaveRequest) ) {
            throw new RuntimeException("RoomType code already exists");
        }
        roomTypeMapper.update(roomTypeSaveRequest, roomTypeToUpdate);
        return roomTypeRepository.save(roomTypeToUpdate);
    }

    @Override
    public void remove(Long id) {
        roomRepository.setRoomTypeToNull(id);
        roomTypeRepository.deleteById(id);
    }
}
