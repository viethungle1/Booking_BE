package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.mapper.request.RoomRequestMapper;
import org.example.minitest1.model.Room;
import org.example.minitest1.dto.request.room.RoomSaveRequest;
import org.example.minitest1.repository.ReservationRepository;
import org.example.minitest1.repository.RoomRepository;
import org.example.minitest1.service.IRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    private final RoomTypeService roomTypeService;

    private final RoomRequestMapper roomRequestMapper;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room update(Long id, RoomSaveRequest roomSaveRequest) {
        Room roomToUpdate = findById(id);
        if (!roomToUpdate.getCode().equals(roomSaveRequest.getCode()) && validationRoomCode(roomSaveRequest)) {
            throw new RuntimeException("Room code already exists");
        }
        roomRequestMapper.mapping(roomSaveRequest, roomToUpdate);
        roomToUpdate.setRoomType(roomTypeService.findById(roomSaveRequest.getRoomTypeId()));
        return save(roomToUpdate);
    }

    @Override
    public Room create(RoomSaveRequest roomSaveRequest){
        boolean check = validationRoomCode(roomSaveRequest);
        if (check) {
            throw new RuntimeException("Room Code already exist");
        } else {
            Room room = roomRequestMapper.to(roomSaveRequest);
            room.setRoomType(roomTypeService.findById(roomSaveRequest.getRoomTypeId()));
           return save(room);
        }
    }

    @Override
    public void remove(Long id) {
        reservationRepository.setRoomToNull(id);
        roomRepository.deleteById(id);
    }

    public boolean validationRoomCode(RoomSaveRequest roomSaveRequest) {
        return roomRepository.existsByCode(roomSaveRequest.getCode());
    }

}
