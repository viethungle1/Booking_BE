package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.model.Reservation;
import org.example.minitest1.repository.ReservationRepository;
import org.example.minitest1.service.IReservationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {
    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void remove(Long id) {
        reservationRepository.deleteById(id);
    }

//    public void setRoomIdToNull(Long roomId) {
//        reservationRepository.setRoomToNull(roomId);
//    }
}
