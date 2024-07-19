package org.example.minitest1.dto.request.room;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomSaveRequest {
    @NotBlank(message = "Code đang để trống")
    String code;

    @NotBlank(message = "Tên đang để trống")
    String name;

    @NotBlank(message = "Mô tả đang để trống")
    String description;

    @NotBlank(message = "Sàn đang để trống")
    String floor;

    Long roomTypeId;
}
