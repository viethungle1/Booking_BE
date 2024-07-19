package org.example.minitest1.mapper.request;

import org.example.minitest1.dto.request.reservation.ReservationSaveRequest;
import org.example.minitest1.mapper.IMapper;
import org.example.minitest1.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReservationMapper extends IMapper<Reservation, ReservationSaveRequest> {
    void updateReservation(ReservationSaveRequest dto, @MappingTarget Reservation entity);
}
