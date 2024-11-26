package com.example.solana.certbadgemanager.service;

import com.example.solana.certbadgemanager.dto.AuthRequest;
import com.example.solana.certbadgemanager.dto.AuthResponse;
import com.example.solana.certbadgemanager.dto.RegisterRequest;
import com.example.solana.certbadgemanager.model.User;
import com.example.solana.certbadgemanager.repository.UserRepository;
import com.example.solana.certbadgemanager.util.JwtTokenUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public void register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials!");
        }

        String token = jwtTokenUtil.generateToken(user.getUsername());
        return new AuthResponse(token);
    }
}
