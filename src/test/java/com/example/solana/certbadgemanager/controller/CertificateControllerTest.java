package com.example.solana.certbadgemanager.controller;

import com.example.solana.certbadgemanager.dto.CertificateRequestDTO;
import com.example.solana.certbadgemanager.dto.CertificateResponseDTO;
import com.example.solana.certbadgemanager.service.CertificateService;
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

@WebMvcTest(CertificateController.class)
class CertificateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CertificateService certificateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCertificate_ShouldReturnCreatedCertificate() throws Exception {
        CertificateResponseDTO certificate = new CertificateResponseDTO();
        certificate.setId(String.valueOf(1L));
        certificate.setTitle("Completion Certificate");

        when(certificateService.createCertificate(any(CertificateRequestDTO.class))).thenReturn(certificate);

        mockMvc.perform(post("/api/certificates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"Completion Certificate\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Completion Certificate"));

        verify(certificateService, times(1)).createCertificate(any(CertificateRequestDTO.class));
    }

    @Test
    void getCertificateById_ShouldReturnCertificate() throws Exception {
        CertificateResponseDTO certificate = new CertificateResponseDTO();
        certificate.setId(String.valueOf(1L));
        certificate.setTitle("Completion Certificate");

        when(certificateService.getCertificateById(String.valueOf(1L))).thenReturn(certificate);

        mockMvc.perform(get("/api/certificates/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Completion Certificate"));

        verify(certificateService, times(1)).getCertificateById(String.valueOf(1L));
    }
}
