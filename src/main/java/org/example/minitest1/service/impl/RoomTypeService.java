package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.mapper.request.RoomTypeMapper;
import org.example.minitest1.model.RoomType;
import org.example.minitest1.dto.request.roomtype.RoomTypeSaveRequest;
import org.example.minitest1.repository.RoomTypeRepository;
import org.example.minitest1.service.IRoomTypeService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeService implements IRoomTypeService {
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

    public boolean isCheck(RoomTypeSaveRequest roomTypeSaveRequest) {
        return roomTypeRepository.existsByCode(roomTypeSaveRequest.getCode());
    }

    public void updateRoomTypeFromDto(RoomType roomType,RoomTypeSaveRequest roomTypeSaveRequest) {
        roomType.setCode(roomTypeSaveRequest.getCode());
        roomType.setName(roomTypeSaveRequest.getName());
        roomType.setDescription(roomTypeSaveRequest.getDescription());
        roomType.setSize(roomTypeSaveRequest.getSize());
        roomType.setNumOfBed(roomTypeSaveRequest.getNumOfBed());
        roomType.setMaxAdults(roomTypeSaveRequest.getMaxAdults());
        roomType.setMaxChild(roomTypeSaveRequest.getMaxChild());
    }

    public void createNewRoomType(RoomTypeSaveRequest roomTypeSaveRequest){
        boolean check = isCheck(roomTypeSaveRequest);
        if (!check) {
            RoomType roomType = roomTypeMapper.to(roomTypeSaveRequest);
            roomTypeRepository.save(roomType);
        } else {
            throw new RuntimeException("RoomType Code already exist");
        }
    }

    @Override
    public void remove(Long id) {
        roomTypeRepository.deleteById(id);
    }
}
