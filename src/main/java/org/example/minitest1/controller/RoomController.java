package org.example.minitest1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.minitest1.model.Room;
import org.example.minitest1.dto.request.room.RoomSaveRequest;
import org.example.minitest1.service.impl.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {
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
    public ResponseEntity<?> createRoom(@RequestBody @Valid RoomSaveRequest roomSaveRequest) {
        try {
            return new ResponseEntity<>(roomService.create(roomSaveRequest),HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRoom(@PathVariable Long id, @RequestBody @Valid RoomSaveRequest roomSaveRequest) {
        try {
            return new ResponseEntity<>(roomService.update(id, roomSaveRequest),HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        roomService.remove(id);
        return ResponseEntity.ok("Room deleted successfully");
    }
}
