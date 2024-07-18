package org.example.minitest1.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.minitest1.model.RoomType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDto {
    public static final String NOT_EMPTY_MESSAGE = "đang để trống";
    @NotBlank(message = "Code "+NOT_EMPTY_MESSAGE)
    String code;
    @NotBlank(message = "Tên "+NOT_EMPTY_MESSAGE)
    String name;
    @NotBlank(message = "Mô tả "+NOT_EMPTY_MESSAGE)
    String description;
    @NotBlank(message = "Sàn "+NOT_EMPTY_MESSAGE)
    String floor;
    @NotNull(message = "Chưa chọn loại phòng")
    RoomType roomType;
}
