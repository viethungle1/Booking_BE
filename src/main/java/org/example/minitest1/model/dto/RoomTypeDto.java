package org.example.minitest1.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypeDto {
    public static final String NOT_EMPTY_MESSAGE = "đang để trống";
    public static final String GREAT_THAN_ZERO = "phải lớn hơn 0";
    @NotBlank(message = "Code "+NOT_EMPTY_MESSAGE)
    String code;
    @NotBlank(message = "Tên "+NOT_EMPTY_MESSAGE)
    String name;
    @NotBlank(message = "Mô tả "+NOT_EMPTY_MESSAGE)
    String description;
    @Min(value = 1, message = "Size "+GREAT_THAN_ZERO)
    Integer size;
    @Min(value = 1, message = "Số phòng ngủ "+GREAT_THAN_ZERO)
    Integer numOfBed;
    @Min(value = 1, message = "Số lượng người lớn tối đa "+GREAT_THAN_ZERO)
    Integer maxAdults;
    @Min(value = 1, message = "Số lượng trẻ em tối đa "+GREAT_THAN_ZERO)
    Integer maxChild;
}
