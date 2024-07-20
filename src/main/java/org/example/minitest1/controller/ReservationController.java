package org.example.minitest1.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.minitest1.model.Reservation;
import org.example.minitest1.dto.request.reservation.ReservationSaveRequest;
import org.example.minitest1.service.impl.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> findAll() {
        return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> findById(@PathVariable Long id) {
        return new ResponseEntity<>(reservationService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid ReservationSaveRequest reservationSaveRequest) {
        try {
            return new ResponseEntity<>(reservationService.create(reservationSaveRequest),HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ReservationSaveRequest reservationSaveRequest) {
        try {
            return new ResponseEntity<>(reservationService.update(id, reservationSaveRequest),HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        reservationService.remove(id);
        return ResponseEntity.ok("Reservation deleted successfully");
    }

}
