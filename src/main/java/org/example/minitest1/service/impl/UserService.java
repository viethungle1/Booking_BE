package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.dto.PasswordChangeDto;
import org.example.minitest1.dto.UserDto;
import org.example.minitest1.model.Role;
import org.example.minitest1.repository.RoleRepository;
import org.example.minitest1.security.jwtService.UserPrinciple;
import org.example.minitest1.repository.UserRepository;
import org.example.minitest1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.minitest1.model.User;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService, IUserService {
    public static final String ROLE_DEFAULT = "ROLE_GUEST";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    //    jwt
    public UserDetails loadUserByUsername(String username) {
        return UserPrinciple.build(userRepository.findByUsername(username));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    public void createNewUser(UserDto user) {
        List<User> users = findAll();
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                throw new RuntimeException("Username already exists");
            }
        }
        User newUser = new User();
        Role role = roleRepository.findOneByName(ROLE_DEFAULT);
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(role);
        userRepository.save(newUser);
    }

    public void changePassword(Long id, PasswordChangeDto passwordChangeDto) {
        User user = findById(id);
        if (!passwordEncoder.matches(passwordChangeDto.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(passwordChangeDto.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        User user = findById(id);
        if (user.getRole().getName().equals(ROLE_ADMIN)) {
            throw new RuntimeException("Unauthorized operation");
        }
        userRepository.deleteById(id);
    }
}
