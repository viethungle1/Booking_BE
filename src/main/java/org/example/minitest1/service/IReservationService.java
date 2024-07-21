package org.example.minitest1.service;

import org.example.minitest1.dto.request.reservation.ReservationSaveRequest;
import org.example.minitest1.dto.response.ReservationResponse;
import org.example.minitest1.model.Reservation;

import java.util.List;

public interface IReservationService extends GeneralService<Reservation>{
    List<ReservationResponse> findAll();

    Reservation create(ReservationSaveRequest roomSaveRequest);

    Reservation update(Long id, ReservationSaveRequest roomSaveRequest);
}
