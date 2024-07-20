package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.mapper.request.ReservationMapper;
import org.example.minitest1.model.Reservation;
import org.example.minitest1.dto.request.reservation.ReservationSaveRequest;
import org.example.minitest1.repository.ReservationRepository;
import org.example.minitest1.service.IReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final RoomService roomService;

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
    public Reservation create(ReservationSaveRequest reservationSaveRequest) {
        boolean isConflict = checkTimeBooking(reservationSaveRequest.getRoomId(),
                reservationSaveRequest.getCreatedDate(),
                reservationSaveRequest.getEndDate());
        if (reservationSaveRequest.getEndDate().isBefore(reservationSaveRequest.getCreatedDate())) {
           throw new RuntimeException("End date must be after or equal to the start date");
        }
        if (isConflict) {
            throw new RuntimeException("Room has been booked");
        } else {
            Reservation reservation = reservationMapper.to(reservationSaveRequest);
            reservation.setRoom(roomService.findById(reservationSaveRequest.getRoomId()));
            return reservationRepository.save(reservation);
        }
    }

    @Override
    public Reservation update(Long id, ReservationSaveRequest reservationSaveRequest) {
        Reservation reservationToUpdate = findById(id);
        if (reservationSaveRequest.getEndDate().isBefore(reservationSaveRequest.getCreatedDate())) {
            throw new RuntimeException("Reservation code already exists");
        }
        reservationMapper.update(reservationSaveRequest, reservationToUpdate);
        reservationToUpdate.setRoom(roomService.findById(reservationSaveRequest.getRoomId()));
        return reservationRepository.save(reservationToUpdate);
    }

    @Override
    public void remove(Long id) {
        reservationRepository.deleteById(id);
    }

    public boolean checkTimeBooking(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        return reservationRepository.existsConflictingReservations(id, startDate, endDate);
    }
}
