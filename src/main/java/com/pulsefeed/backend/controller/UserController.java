package com.pulsefeed.backend.controller;

import com.pulsefeed.backend.dto.LoginRequest;
import com.pulsefeed.backend.model.User;
import com.pulsefeed.backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        try {
            User registerUser = userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully!");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error : " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        String token = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (token != null){
            return ResponseEntity.ok("Bearer token : "+token);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid email or password");
        }
    }
}
