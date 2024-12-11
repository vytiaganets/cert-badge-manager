package com.example.solana.certbadgemanager.controller;

import com.example.solana.certbadgemanager.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/user-info")
    public ResponseEntity<Map<String, String>> getUserInfo() {

        return ResponseEntity.ok(Map.of("name", "John Doe"));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Map<String, Object>>> getTasks() {

        List<Map<String, Object>> tasks = List.of(
                Map.of("id", "1", "title", "Complete project", "progress", 80),
                Map.of("id", "2", "title", "Attend meeting at 2 PM", "progress", 50)
        );
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Map<String, String>>> getMessages() {

        List<Map<String, String>> messages = List.of(
                Map.of("id", "1", "sender", "Admin", "text", "Welcome to the system!"),
                Map.of("id", "2", "sender", "Support", "text", "Your account is updated.")
        );
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> getDashboardMessage() {

        return ResponseEntity.ok("Welcome to the Dashboard! You are authenticated.");
    }
}
