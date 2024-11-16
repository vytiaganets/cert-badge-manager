package com.example.solana.certbadgemanager.repository;

import com.example.solana.certbadgemanager.model.Badge;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class BadgeRepositoryTest {

    @Autowired
    private BadgeRepository badgeRepository;

    @Test
    void saveBadge_ShouldReturnSavedBadge() {
        Badge badge = new Badge();
        badge.setName("Achievement Badge");

        Badge savedBadge = badgeRepository.save(badge);

        assertNotNull(savedBadge.getId());
        assertEquals("Achievement Badge", savedBadge.getName());
    }

    @Test
    void findById_ShouldReturnBadgeIfExists() {
        Badge badge = new Badge();
        badge.setName("Achievement Badge");
        Badge savedBadge = badgeRepository.save(badge);

        Optional<Badge> foundBadge = badgeRepository.findById(savedBadge.getId());

        assertTrue(foundBadge.isPresent());
        assertEquals("Achievement Badge", foundBadge.get().getName());
    }

    @Test
    void deleteById_ShouldRemoveBadge() {
        Badge badge = new Badge();
        badge.setName("Temporary Badge");
        Badge savedBadge = badgeRepository.save(badge);

        badgeRepository.deleteById(savedBadge.getId());

        Optional<Badge> deletedBadge = badgeRepository.findById(savedBadge.getId());
        assertFalse(deletedBadge.isPresent());
    }
}
