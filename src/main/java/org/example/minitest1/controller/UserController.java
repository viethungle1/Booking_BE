package org.example.minitest1.controller;

import lombok.RequiredArgsConstructor;
import org.example.minitest1.dto.PasswordChangeDto;
import org.example.minitest1.model.JwtResponse;
import org.example.minitest1.security.jwtService.JwtTokenProvider;
import org.example.minitest1.model.User;
import org.example.minitest1.service.impl.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Lazy
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByUsername(user.getUsername());
        JwtResponse jwtResponse = new JwtResponse(currentUser.getId(), jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return new ResponseEntity<>(userService.createNewUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/changePassword/{uId}")
    public ResponseEntity<?> changePassword(@PathVariable Long uId, @RequestBody PasswordChangeDto passwordChangeDto) {
        try {
            userService.changePassword(uId,passwordChangeDto);
            return ResponseEntity.ok("Password changed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{uID}")
    public ResponseEntity<?> delete(@PathVariable Long uID) {
        try {
            userService.remove(uID);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
