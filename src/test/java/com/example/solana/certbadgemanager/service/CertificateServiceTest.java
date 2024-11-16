package com.example.solana.certbadgemanager.service;

import com.example.solana.certbadgemanager.dto.CertificateResponseDTO;
import com.example.solana.certbadgemanager.dto.CertificateRequestDTO;
import com.example.solana.certbadgemanager.model.Certificate;
import com.example.solana.certbadgemanager.repository.CertificateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CertificateServiceTest {

    @InjectMocks
    private CertificateService certificateService;

    @Mock
    private CertificateRepository certificateRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCertificate_ShouldReturnSavedCertificate() {
        CertificateRequestDTO certificateDTO = new CertificateRequestDTO();
        certificateDTO.setTitle("Completion Certificate");

        Certificate certificate = new Certificate();
        certificate.setId(String.valueOf(1L));
        certificate.setTitle("Completion Certificate");

        when(certificateRepository.save(any(Certificate.class))).thenReturn(certificate);

        CertificateResponseDTO createdCertificate = certificateService.createCertificate(certificateDTO);

        assertNotNull(createdCertificate);
        assertEquals("Completion Certificate", createdCertificate.getTitle());
        verify(certificateRepository, times(1)).save(any(Certificate.class));
    }

    @Test
    void getCertificateById_ShouldReturnCertificateIfExists() {
        Certificate certificate = new Certificate();
        certificate.setId(String.valueOf(1L));
        certificate.setTitle("Completion Certificate");

        when(certificateRepository.findById(String.valueOf(1L))).thenReturn(Optional.of(certificate));

        CertificateResponseDTO foundCertificate = certificateService.getCertificateById(String.valueOf(1L));

        assertNotNull(foundCertificate);
        assertEquals("Completion Certificate", foundCertificate.getTitle());
        verify(certificateRepository, times(1)).findById(String.valueOf(1L));
    }
}
