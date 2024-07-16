package org.example.minitest1.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class JwtResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(Long id, String token, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.token = token;
        this.email = email;
        this.authorities = authorities;
    }
}
