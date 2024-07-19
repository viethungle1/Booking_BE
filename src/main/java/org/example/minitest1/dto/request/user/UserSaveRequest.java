package org.example.minitest1.dto.request.user;

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
public class UserSaveRequest {
    @NotEmpty(message = "Chưa nhập password")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,}$", message = "Username phải từ 6 kí tự trở lên")
    String username;

    @NotEmpty(message = "Chưa nhập password")
    @Pattern(regexp = "^.{6,}$", message = "Password phải từ 6 kí tự trở lên")
    String password;
}
