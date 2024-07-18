package org.example.minitest1.model.dto;

import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.minitest1.model.Room;

import java.time.LocalDateTime;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationDto {
    @NotBlank(message = "Code đang để trống")
    String code;

    @NotBlank(message = "Tên khách đang để trống")
    String guestName;

    @NotBlank(message = "Id khách đang để trống")
    String guestIdNo;

    @NotBlank(message = "Điện thoại đang để trống")
    String guestPhone;

    @NotBlank(message = "Email đang để trống")
    String guestEmail;

    @NotNull(message = "Thời gian đặt phòng đang để trống")
    LocalDateTime createdDate;

    @NotNull(message = "Thời gian trả phòng đang để trống")
    LocalDateTime endDate;

    @Min(value = 1, message = "Size phải lớn hơn 0")
    Double price;

    @Min(value = 1, message = "Size phải lớn hơn 0")
    Integer status;

    @NotNull(message = "Chưa chọn phòng")
    Room room;
}
