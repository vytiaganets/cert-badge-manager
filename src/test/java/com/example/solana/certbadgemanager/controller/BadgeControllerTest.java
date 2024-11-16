package com.example.solana.certbadgemanager.controller;

import com.example.solana.certbadgemanager.dto.BadgeRequestDTO;
import com.example.solana.certbadgemanager.dto.BadgeResponseDTO;
import com.example.solana.certbadgemanager.service.BadgeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BadgeController.class)
class BadgeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BadgeService badgeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createBadge_ShouldReturnCreatedBadge() throws Exception {
        BadgeResponseDTO badge = new BadgeResponseDTO();
        badge.setId(String.valueOf(1L));
        badge.setName("Achievement Badge");

        when(badgeService.createBadge(any(BadgeRequestDTO.class))).thenReturn(badge);

        mockMvc.perform(post("/api/badges")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Achievement Badge\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Achievement Badge"));

        verify(badgeService, times(1)).createBadge(any(BadgeRequestDTO.class));
    }

    @Test
    void getBadgeById_ShouldReturnBadge() throws Exception {
        BadgeResponseDTO badge = new BadgeResponseDTO();
        badge.setId(String.valueOf(1L));
        badge.setName("Achievement Badge");

        when(badgeService.getBadgeById(String.valueOf(1L))).thenReturn(badge);

        mockMvc.perform(get("/api/badges/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Achievement Badge"));

        verify(badgeService, times(1)).getBadgeById(String.valueOf(1L));
    }
}
