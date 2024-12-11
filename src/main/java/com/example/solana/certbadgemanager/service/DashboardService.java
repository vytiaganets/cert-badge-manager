package com.example.solana.certbadgemanager.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    public String getUserInfo() {
        return "John Doe";
    }

    public List<Map<String, Object>> getTasks() {
        return List.of(
                Map.of("id", "1", "title", "Complete project", "progress", 80),
                Map.of("id", "2", "title", "Attend meeting at 2 PM", "progress", 50)
        );
    }

    public List<Map<String, String>> getMessages() {

        return List.of(
                Map.of("id", "1", "sender", "Admin", "text", "Welcome to the system!"),
                Map.of("id", "2", "sender", "Support", "text", "Your account is updated.")
        );
    }

    public String getDashboardMessage() {

        return "Welcome to the Dashboard! You are authenticated.";
    }
}
