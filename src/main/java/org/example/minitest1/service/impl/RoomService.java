package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.mapper.request.RoomMapper;
import org.example.minitest1.model.Room;
import org.example.minitest1.dto.request.room.RoomSaveRequest;
import org.example.minitest1.repository.RoomRepository;
import org.example.minitest1.service.IRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final RoomTypeService roomTypeService;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public boolean isCheck(RoomSaveRequest roomDto) {
        return roomRepository.existsByCode(roomDto.getCode());
    }

    public void updateRoomFromDto(Room room,RoomSaveRequest roomSaveRequest) {
        roomMapper.updateRoom(roomSaveRequest, room);
        room.setRoomType(roomTypeService.findById(roomSaveRequest.getRoomTypeId()));
    }

    public void createNewRoom(RoomSaveRequest roomSaveRequest){
        boolean check = isCheck(roomSaveRequest);
        if (!check) {
            Room room = roomMapper.to(roomSaveRequest);
            room.setRoomType(roomTypeService.findById(roomSaveRequest.getRoomTypeId()));
            roomRepository.save(room);
        } else {
            throw new RuntimeException("Room Code already exist");
        }
    }

    public void setRoomTypeNull(Long id){
        roomRepository.setRoomTypeToNull(id);
    }

    @Override
    public void remove(Long id) {
        roomRepository.deleteById(id);
    }
}
