package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.model.RoomType;
import org.example.minitest1.model.dto.RoomTypeDto;
import org.example.minitest1.repository.RoomTypeRepository;
import org.example.minitest1.service.IRoomTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeService implements IRoomTypeService {
    private final RoomTypeRepository roomTypeRepository;

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

    public boolean isCheck(RoomTypeDto roomTypeDto) {
        return roomTypeRepository.existsByCode(roomTypeDto.getCode());
    }

    public void updateRoomTypeFromDto(RoomType roomType,RoomTypeDto roomTypeDto) {
        roomType.setCode(roomTypeDto.getCode());
        roomType.setName(roomTypeDto.getName());
        roomType.setDescription(roomTypeDto.getDescription());
        roomType.setSize(roomTypeDto.getSize());
        roomType.setNumOfBed(roomTypeDto.getNumOfBed());
        roomType.setMaxAdults(roomTypeDto.getMaxAdults());
        roomType.setMaxChild(roomTypeDto.getMaxChild());
    }

    public void createNewRoomType(RoomTypeDto roomTypeDto){
        boolean check = isCheck(roomTypeDto);
        if (!check) {
            RoomType roomType = new RoomType();
            updateRoomTypeFromDto(roomType, roomTypeDto);
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
