package org.example.minitest1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty(message = "Chưa nhập password")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,}$", message = "Username phải từ 6 kí tự trở lên")
    private String username;

    @NotEmpty(message = "Chưa nhập password")
    @Pattern(regexp = "^.{6,}$", message = "Password phải từ 8 kí tự trở lên")
    private String password;
}
