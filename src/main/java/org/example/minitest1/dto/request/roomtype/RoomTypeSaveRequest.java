package org.example.minitest1.dto.request.roomtype;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomTypeSaveRequest {
    @NotBlank(message = "Code đang để trống")
    String code;

    @NotBlank(message = "Tên đang để trống")
    String name;

    @NotBlank(message = "Mô tả đang để trống")
    String description;

    @Min(value = 1, message = "Size phải lớn hơn 0")
    Integer size;

    @Min(value = 1, message = "Số phòng ngủ phải lớn hơn 0")
    Integer numOfBed;

    @Min(value = 1, message = "Số lượng người lớn tối đa phải lớn hơn 0")
    Integer maxAdults;

    @Min(value = 1, message = "Số lượng trẻ em tối đa phải lớn hơn 0")
    Integer maxChild;
}
