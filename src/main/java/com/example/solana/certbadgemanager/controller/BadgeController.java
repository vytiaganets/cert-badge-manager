package com.example.solana.certbadgemanager.controller;

import com.example.solana.certbadgemanager.dto.BadgeRequestDTO;
import com.example.solana.certbadgemanager.dto.BadgeResponseDTO;
import com.example.solana.certbadgemanager.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {
    private final BadgeService badgeService;

    @Autowired
    public BadgeController(BadgeService badgeService){
        this.badgeService = badgeService;
    }

    @PostMapping("/create")
    public ResponseEntity<BadgeResponseDTO> createBadge(@RequestBody BadgeRequestDTO badgeRequestDTO){
        BadgeResponseDTO badgeResponse = badgeService.createBadge(badgeRequestDTO);
        return ResponseEntity.ok(badgeResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BadgeResponseDTO> getBadgeById(@PathVariable String id){
        BadgeResponseDTO badgeResponse = badgeService.getBadgeById(id);
        return ResponseEntity.ok(badgeResponse);
    }
}
