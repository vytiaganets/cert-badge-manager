package com.example.solana.certbadgemanager.service;

import com.example.solana.certbadgemanager.dto.BadgeResponseDTO;
import com.example.solana.certbadgemanager.model.Badge;
import com.example.solana.certbadgemanager.repository.BadgeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BadgeServiceTest {

    @InjectMocks
    private BadgeService badgeService;

    @Mock
    private BadgeRepository badgeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBadge_ShouldReturnSavedBadge() {
        BadgeResponseDTO badgeDTO = new BadgeResponseDTO();
        badgeDTO.setName("Achievement Badge");

        Badge badge = new Badge();
        badge.setId(String.valueOf(1L));
        badge.setName("Achievement Badge");

        when(badgeRepository.save(any(Badge.class))).thenReturn(badge);

        BadgeResponseDTO createdBadge = badgeService.createBadge(badgeResponseDTO);

        assertNotNull(createdBadge);
        assertEquals("Achievement Badge", createdBadge.getName());
        verify(badgeRepository, times(1)).save(any(Badge.class));
    }

    @Test
    void getBadgeById_ShouldReturnBadgeIfExists() {
        Badge badge = new Badge();
        badge.setId(String.valueOf(1L));
        badge.setName("Achievement Badge");

        when(badgeRepository.findById(String.valueOf(1L))).thenReturn(Optional.of(badge));

        BadgeResponseDTO foundBadge = badgeService.getBadgeById(String.valueOf(1L));

        assertNotNull(foundBadge);
        assertEquals("Achievement Badge", foundBadge.getName());
        verify(badgeRepository, times(1)).findById(String.valueOf(1L));
    }
}
