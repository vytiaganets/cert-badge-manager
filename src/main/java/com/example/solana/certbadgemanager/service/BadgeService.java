package com.example.solana.certbadgemanager.service;

import com.example.solana.certbadgemanager.dto.BadgeRequestDTO;
import com.example.solana.certbadgemanager.dto.BadgeResponseDTO;
import com.example.solana.certbadgemanager.model.Badge;
import com.example.solana.certbadgemanager.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;

    @Autowired
    public BadgeService(BadgeRepository badgeRepository){
        this.badgeRepository = badgeRepository;
    }

    public BadgeResponseDTO createBadge(BadgeRequestDTO badgeRequestDTO){
        Badge badge = new Badge();
        badge.setName(badgeRequestDTO.getName());
        badge.setDescription(badgeRequestDTO.getDescription());

        Badge savedBadge = badgeRepository.save(badge);

        return new BadgeResponseDTO(savedBadge.getId(), savedBadge.getName(), savedBadge.getDescription());
    }

    public BadgeResponseDTO getBadgeById(String id){
        Badge badge = badgeRepository.findById(id).orElseThrow(() -> new RuntimeException("Badge not found"));
        return new BadgeResponseDTO(badge.getId(), badge.getName(), badge.getDescription());
    }
}
