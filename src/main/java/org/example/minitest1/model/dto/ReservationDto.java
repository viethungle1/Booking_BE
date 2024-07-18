package org.example.minitest1.model.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationDto {
    @NotBlank
    String code;
    @NotBlank
    String guestName;
    @NotBlank
    String guestIdNo;
    @NotBlank
    String guestPhone;
    @NotBlank
    String guestEmail;
    @NotNull(message = "Created date is required")
    Date createdDate;
    @NotNull(message = "End date is required")
    Date endDate;
    @NotBlank
    Double price;
    @NotBlank
    Integer status;
    @NotNull
    Long roomId;

    @AssertTrue(message = "End date must be after or equal to the created date")
    public boolean isEndDateValid() {
        if (createdDate == null || endDate == null) {
            return true;
        }
        return !endDate.before(createdDate);
    }
}
