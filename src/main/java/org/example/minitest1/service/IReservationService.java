package org.example.minitest1.service;

import org.example.minitest1.dto.request.reservation.ReservationSaveRequest;
import org.example.minitest1.model.Reservation;

public interface IReservationService extends GeneralService<Reservation>{
    Reservation create(ReservationSaveRequest roomSaveRequest);

    Reservation update(Long id, ReservationSaveRequest roomSaveRequest);
}
