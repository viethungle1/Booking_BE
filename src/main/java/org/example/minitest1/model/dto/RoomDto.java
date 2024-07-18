package org.example.minitest1.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.example.minitest1.model.RoomType;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDto {
    @NotBlank(message = "Code đang để trống")
    String code;

    @NotBlank(message = "Tên đang để trống")
    String name;

    @NotBlank(message = "Mô tả đang để trống")
    String description;

    @NotBlank(message = "Sàn đang để trống")
    String floor;

    @NotNull(message = "Chưa chọn loại phòng")
    RoomType roomType;
}
