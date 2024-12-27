package com.example.avywhale.service;

import com.example.avywhale.dto.BadgeRequestDTO;
import com.example.avywhale.dto.BadgeResponseDTO;
import com.example.avywhale.model.Badge;
import com.example.avywhale.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BadgeService {
    private final BadgeRepository badgeRepository;

    @Autowired
    public BadgeService(BadgeRepository badgeRepository){
        this.badgeRepository = badgeRepository;
    }

    public BadgeResponseDTO createBadge(BadgeRequestDTO badgeRequestDTO){
        if (badgeRequestDTO.getName() == null || badgeRequestDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Badge name cannot be null or empty.");
        }
        if (badgeRepository.existsByName(badgeRequestDTO.getName())) {
            throw new IllegalArgumentException("Badge with this name already exists.");
        }

        Badge badge = new Badge();
        badge.setName(badgeRequestDTO.getName());
        badge.setDescription(badgeRequestDTO.getDescription());
        badge.setAwardedDate(LocalDate.now());
        badge.setIssuer("Default Issuer");
        Badge savedBadge = badgeRepository.save(badge);

        return new BadgeResponseDTO(savedBadge.getId(), savedBadge.getName(), savedBadge.getDescription(),
                savedBadge.getAwardedTo(), savedBadge.getAwardedDate(), savedBadge.getIssuer());
    }

    public BadgeResponseDTO getBadgeById(Long id){
        Badge badge = badgeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Badge not found with id: " + id));
        return new BadgeResponseDTO(badge.getId(), badge.getName(), badge.getDescription(), badge.getAwardedTo(),
                badge.getAwardedDate(), badge.getIssuer());
    }

    public List<BadgeResponseDTO> getAllBadges() {
        return List.of(
                new BadgeResponseDTO(1L, "Badge 1", "First badge description", "John Doe", LocalDate.now(), "My Issuer"),
                new BadgeResponseDTO(2L, "Badge 2", "Second badge description", "Jane Smith", LocalDate.now(), "Another Issuer")
        );
    }

}
