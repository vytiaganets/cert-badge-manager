package com.example.solana.certbadgemanager.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CertificateDTOTest {

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(CertificateResponseDTO.class).verify();
    }

    @Test
    void testSettersAndGetters() {
        CertificateResponseDTO certificateDTO = new CertificateResponseDTO();
        certificateDTO.setId(String.valueOf(1L));
        certificateDTO.setTitle("Completion Certificate");
        certificateDTO.setRecipient("John Doe");

        assertEquals(1L, certificateDTO.getId());
        assertEquals("Completion Certificate", certificateDTO.getTitle());
        assertEquals("John Doe", certificateDTO.getRecipient());
    }

    @Test
    void testToString() {
        CertificateResponseDTO certificateDTO = new CertificateResponseDTO();
        certificateDTO.setId(String.valueOf(1L));
        certificateDTO.setTitle("Completion Certificate");

        String expected = "CertificateDTO{id=1, title='Completion Certificate'}";
        assertEquals(expected, certificateDTO.toString());
    }
}
