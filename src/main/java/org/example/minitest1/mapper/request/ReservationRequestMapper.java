package org.example.minitest1.mapper.request;

import org.example.minitest1.dto.request.reservation.ReservationSaveRequest;
import org.example.minitest1.mapper.IMapper;
import org.example.minitest1.model.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationRequestMapper extends IMapper<Reservation, ReservationSaveRequest> {
}
