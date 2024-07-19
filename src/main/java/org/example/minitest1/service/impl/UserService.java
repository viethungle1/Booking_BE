package org.example.minitest1.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.dto.request.user.PasswordChangeSaveRequest;
import org.example.minitest1.dto.request.user.UserSaveRequest;
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

    public void createNewUser(UserSaveRequest userSaveRequest) {
        boolean check = userRepository.existsByUsername(userSaveRequest.getUsername());
        if (!check) {
            User newUser = new User();
            Role role = roleRepository.findOneByName(ROLE_DEFAULT);
            newUser.setUsername(userSaveRequest.getUsername());
            newUser.setPassword(passwordEncoder.encode(userSaveRequest.getPassword()));
            newUser.setRole(role);
            userRepository.save(newUser);
        } else {
            throw new RuntimeException("username already exists");
        }
    }

    public void changePassword(Long id, PasswordChangeSaveRequest passwordChangeSaveRequest) {
        User user = findById(id);
        if (!passwordEncoder.matches(passwordChangeSaveRequest.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(passwordChangeSaveRequest.getNewPassword()));
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
