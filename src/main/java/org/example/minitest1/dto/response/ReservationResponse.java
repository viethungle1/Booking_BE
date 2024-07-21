package org.example.minitest1.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationResponse {
    Long id;
    String code;
    String guestName;
    String guestIdNo;
    String guestPhone;
    String guestEmail;
    LocalDateTime createdDate;
    LocalDateTime endDate;
    Double price;
    Integer status;
    String roomName;
    String roomTypeName;
}
