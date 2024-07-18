package org.example.minitest1.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordChangeDto {
    String currentPassword;
    @NotEmpty(message = "Chưa nhập password")
    @Pattern(regexp = "^.{6,}$", message = "Password phải từ 8 kí tự trở lên")
    String newPassword;
}
