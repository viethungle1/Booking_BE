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
            reservationService.createReservation(reservationSaveRequest);
            return ResponseEntity.ok("Room created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ReservationSaveRequest reservationSaveRequest) {
        Reservation reservation = reservationService.findById(id);
        if (reservation == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reservation not found");
        }
        if (reservationSaveRequest.getEndDate().isBefore(reservationSaveRequest.getCreatedDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("End date must be after or equal to the start date");
        }
        reservationService.updateReservationFromDto(reservation, reservationSaveRequest);
        reservationService.save(reservation);
        return ResponseEntity.ok("Reservation updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        reservationService.remove(id);
        return ResponseEntity.ok("Reservation deleted successfully");
    }

}
