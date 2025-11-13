package com.pulsefeed.backend.services;

import com.pulsefeed.backend.model.User;
import com.pulsefeed.backend.repository.UserRepository;
import com.pulsefeed.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return jwtUtil.generateToken(email);
            } else {
                throw new RuntimeException("Invalid password!");
            }
        } else {
            throw new RuntimeException("User not found!");
        }
    }
}
