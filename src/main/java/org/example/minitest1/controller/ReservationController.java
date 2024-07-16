package org.example.minitest1.controller;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.model.Reservation;
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
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.save(reservation),HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Reservation> update(@RequestBody Reservation reservation) {
        return new ResponseEntity<>(reservationService.save(reservation),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Reservation> delete(@PathVariable Long id) {
        reservationService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
