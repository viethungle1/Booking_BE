package org.example.minitest1.mapper.response;

import org.example.minitest1.dto.response.ReservationResponse;
import org.example.minitest1.mapper.IMapper;
import org.example.minitest1.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationResponseMapper extends IMapper<Reservation, ReservationResponse> {

    @Mapping(source = "room.name", target = "roomName")
    @Mapping(source = "room.roomType.name", target = "roomTypeName")
    ReservationResponse toReservationResponse(Reservation reservation);

    List<ReservationResponse> toReservationResponseList(List<Reservation> reservations);
}
