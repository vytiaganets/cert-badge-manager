//package com.example.solana.certbadgemanager.controller;
//
//import com.example.solana.certbadgemanager.dto.BadgeRequestDTO;
//import com.example.solana.certbadgemanager.dto.BadgeResponseDTO;
//import com.example.solana.certbadgemanager.service.BadgeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(BadgeController.class)
//public class BadgeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BadgeService badgeService;
//
//    @BeforeEach
//    public void setUp(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void createBadge_ShouldReturnCreatedBadge() throws Exception{
//        BadgeResponseDTO badge = new BadgeResponseDTO();
//        badge.setId(String.valueOf(1L));
//        badge.setName("Achievement Badge");
//        badge.setDescription("Achievement Badge Description");
//
//        when(badgeService.createBadge(any(BadgeRequestDTO.class))).thenReturn(badge);
//
//        mockMvc.perform(post("/api/badges")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\": \"Achievement Badge\"}"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.name").value("Achievement Badge"))
//                .andExpect(jsonPath("$.description").value("Achievement Badge Description"));
//        verify(badgeService, times(1)).createBadge(any(BadgeRequestDTO.class));
//    }
//}
