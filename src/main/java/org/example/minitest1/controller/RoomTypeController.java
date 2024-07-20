package org.example.minitest1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.minitest1.model.RoomType;
import org.example.minitest1.dto.request.roomtype.RoomTypeSaveRequest;
import org.example.minitest1.service.impl.RoomTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/roomType")
@RequiredArgsConstructor
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    @GetMapping
    public ResponseEntity<List<RoomType>> getAllRoomTypes() {
        return new ResponseEntity<>(roomTypeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomType> getRoomTypeById(@PathVariable Long id) {
        return new ResponseEntity<>(roomTypeService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRoomType(@RequestBody @Valid RoomTypeSaveRequest roomTypeSaveRequest) {
        try {
            return new ResponseEntity<>(roomTypeService.create(roomTypeSaveRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRoomType(@PathVariable Long id, @RequestBody @Valid RoomTypeSaveRequest roomTypeSaveRequest) {
        try {
            return new ResponseEntity<>(roomTypeService.update(id, roomTypeSaveRequest),HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoomType(@PathVariable Long id) {
        roomTypeService.remove(id);
        return ResponseEntity.ok("RoomType deleted successfully");
    }
}
