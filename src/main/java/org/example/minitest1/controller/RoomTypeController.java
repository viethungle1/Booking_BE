package org.example.minitest1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.minitest1.model.RoomType;
import org.example.minitest1.dto.request.roomtype.RoomTypeSaveRequest;
import org.example.minitest1.service.impl.RoomService;
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
    private final RoomService roomService;
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
            roomTypeService.createNewRoomType(roomTypeSaveRequest);
            return ResponseEntity.ok("RoomType created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRoomType(@PathVariable Long id,
                                            @RequestBody @Valid RoomTypeSaveRequest roomTypeSaveRequest) {
        RoomType roomType = roomTypeService.findById(id);
        if (roomType == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("RoomType not found");
        }
        if (!roomTypeSaveRequest.getCode().equals(roomType.getCode()) && roomTypeService.isCheck(roomTypeSaveRequest) ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RoomType code already exists");
        }
        roomTypeService.updateRoomTypeFromDto(roomType, roomTypeSaveRequest);
        roomTypeService.save(roomType);
        return ResponseEntity.ok("RoomType updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoomType(@PathVariable Long id) {
        roomService.setRoomTypeNull(id);
        roomTypeService.remove(id);
        return ResponseEntity.ok("RoomType deleted successfully");
    }

}
