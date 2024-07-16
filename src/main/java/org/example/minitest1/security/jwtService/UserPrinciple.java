package org.example.minitest1.security.jwtService;

import org.example.minitest1.model.Role;
import org.example.minitest1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serial;
import java.util.Collection;
import java.util.List;

public class UserPrinciple implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> roles;

    public UserPrinciple(String email, String password, Collection<? extends GrantedAuthority> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrinciple build(User user) {
        Role role = user.getRole();
        GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
        return new UserPrinciple(user.getUsername(), user.getPassword(), List.of(authority));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
