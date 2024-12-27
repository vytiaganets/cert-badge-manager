package com.example.avywhale.controller;

import com.example.avywhale.dto.BadgeRequestDTO;
import com.example.avywhale.dto.BadgeResponseDTO;
import com.example.avywhale.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/badges")
public class BadgeController {
    private final BadgeService badgeService;

    @Autowired
    public BadgeController(BadgeService badgeService){
        this.badgeService = badgeService;
    }

    @PostMapping("/create")
    public ResponseEntity<BadgeResponseDTO> createBadge(@RequestBody BadgeRequestDTO badgeRequestDTO) {
        BadgeResponseDTO badgeResponse = badgeService.createBadge(badgeRequestDTO);
        return ResponseEntity.ok(badgeResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BadgeResponseDTO> getBadgeById(@PathVariable Long id){
        BadgeResponseDTO badgeResponse = badgeService.getBadgeById(id);
        return ResponseEntity.ok(badgeResponse);
    }
    @GetMapping
    public ResponseEntity<List<BadgeResponseDTO>> getAllBadges() {
        List<BadgeResponseDTO> badges = badgeService.getAllBadges();
        List<BadgeResponseDTO> badgeResponseDTOs = badges.stream()
                .map(badge -> new BadgeResponseDTO(
                        badge.getId(),
                        badge.getName(),
                        badge.getDescription(),
                        badge.getAwardedTo(),
                        badge.getAwardedDate(),
                        badge.getIssuer()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(badgeResponseDTOs);
    }
}
