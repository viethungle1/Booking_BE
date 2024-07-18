package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.model.Reservation;
import org.example.minitest1.model.dto.ReservationDto;
import org.example.minitest1.repository.ReservationRepository;
import org.example.minitest1.service.IReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void createReservation(ReservationDto reservationDto) {
        boolean isConflict = checkTimeBooking(reservationDto.getRoom().getId(),
                reservationDto.getCreatedDate(),
                reservationDto.getEndDate());
        if (reservationDto.getEndDate().isBefore(reservationDto.getCreatedDate())) {
           throw new RuntimeException("End date must be after or equal to the start date");
        }
        if (!isConflict) {
            Reservation reservation = new Reservation();
            Reservation reservation1 = updateReservationFromDto(reservation,reservationDto);
            reservationRepository.save(reservation1);
        } else {
            throw new RuntimeException("Room has been booked");
        }
    }

    public Reservation updateReservationFromDto(Reservation reservation,ReservationDto reservationDto) {
        reservation.setCode(reservationDto.getCode());
        reservation.setGuestName(reservationDto.getGuestName());
        reservation.setGuestIdNo(reservationDto.getGuestIdNo());
        reservation.setGuestPhone(reservationDto.getGuestPhone());
        reservation.setGuestEmail(reservationDto.getGuestEmail());
        reservation.setCreatedDate(reservationDto.getCreatedDate());
        reservation.setEndDate(reservationDto.getEndDate());
        reservation.setPrice(reservationDto.getPrice());
        reservation.setStatus(reservationDto.getStatus());
        reservation.setRoom(reservationDto.getRoom());
        return reservation;
    }

    @Override
    public void remove(Long id) {
        reservationRepository.deleteById(id);
    }

    public void setRoomIdToNull(Long roomId) {
        reservationRepository.setRoomToNull(roomId);
    }

    public boolean checkTimeBooking(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        return reservationRepository.existsConflictingReservations(id, startDate, endDate);
    }
}
