package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.mapper.request.ReservationMapper;
import org.example.minitest1.model.Reservation;
import org.example.minitest1.dto.request.reservation.ReservationSaveRequest;
import org.example.minitest1.model.Room;
import org.example.minitest1.repository.ReservationRepository;
import org.example.minitest1.repository.RoomRepository;
import org.example.minitest1.service.IReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
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

    public void createReservation(ReservationSaveRequest reservationSaveRequest) {
        boolean isConflict = checkTimeBooking(reservationSaveRequest.getRoomId(),
                reservationSaveRequest.getCreatedDate(),
                reservationSaveRequest.getEndDate());
        if (reservationSaveRequest.getEndDate().isBefore(reservationSaveRequest.getCreatedDate())) {
           throw new RuntimeException("End date must be after or equal to the start date");
        }
        if (!isConflict) {
            Reservation reservation = reservationMapper.to(reservationSaveRequest);
            reservation.setRoom(roomService.findById(reservationSaveRequest.getRoomId()));
            reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Room has been booked");
        }
    }

    public void updateReservationFromDto(Reservation reservation, ReservationSaveRequest reservationSaveRequest) {
        reservation.setCode(reservationSaveRequest.getCode());
        reservation.setGuestName(reservationSaveRequest.getGuestName());
        reservation.setGuestIdNo(reservationSaveRequest.getGuestIdNo());
        reservation.setGuestPhone(reservationSaveRequest.getGuestPhone());
        reservation.setGuestEmail(reservationSaveRequest.getGuestEmail());
        reservation.setCreatedDate(reservationSaveRequest.getCreatedDate());
        reservation.setEndDate(reservationSaveRequest.getEndDate());
        reservation.setPrice(reservationSaveRequest.getPrice());
        reservation.setStatus(reservationSaveRequest.getStatus());
        Room room = roomRepository.findById(reservationSaveRequest.getRoomId()).orElse(null);
        reservation.setRoom(room);
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
