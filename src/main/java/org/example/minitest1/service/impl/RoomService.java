package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.model.Room;
import org.example.minitest1.model.dto.RoomDto;
import org.example.minitest1.repository.RoomRepository;
import org.example.minitest1.service.IRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;

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

    public boolean isCheck(RoomDto roomDto) {
        return roomRepository.existsByCode(roomDto.getCode());
    }

    public void updateRoomFromDto(Room room,RoomDto roomDto) {
        room.setCode(roomDto.getCode());
        room.setName(roomDto.getName());
        room.setDescription(roomDto.getDescription());
        room.setFloor(roomDto.getFloor());
        room.setRoomType(roomDto.getRoomType());
    }

    public void createNewRoom(RoomDto roomDto){
        boolean check = isCheck(roomDto);
        if (!check) {
            Room room = new Room();
            updateRoomFromDto(room,roomDto);
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
