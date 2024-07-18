package org.example.minitest1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.minitest1.model.Room;
import org.example.minitest1.model.dto.RoomDto;
import org.example.minitest1.service.impl.ReservationService;
import org.example.minitest1.service.impl.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
    private final ReservationService reservationService;
    private final RoomService roomService;

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return new ResponseEntity<>(roomService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {
        return new ResponseEntity<>(roomService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRoom(@RequestBody @Valid RoomDto roomDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMessages);
        }
        try {
            roomService.createNewRoom(roomDto);
            return ResponseEntity.ok("Room created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id,
                                        @RequestBody @Valid RoomDto roomDto,
                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMessages);
        }
        Room room = roomService.findById(id);
        if (room == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }

        if (!room.getCode().equals(roomDto.getCode()) && roomService.isCheck(roomDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room code already exists");
        }
        roomService.updateRoomFromDto(room,roomDto);
        roomService.save(room);
        return ResponseEntity.ok("Room updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
//        reservationService.setRoomIdToNull(id);
        roomService.remove(id);
        return ResponseEntity.ok("Room deleted successfully");
    }
}
