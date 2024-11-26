package com.example.solana.certbadgemanager.service;

import com.example.solana.certbadgemanager.model.User;
import com.example.solana.certbadgemanager.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("User already exists!");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public void registerUser(String username, String email, String password, String walletAddress) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("User already exists!");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("USER");
        user.setTokenBalance(0L);
        user.setWalletAddress("N/A".equals(walletAddress) ? null : walletAddress);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
    }


}
