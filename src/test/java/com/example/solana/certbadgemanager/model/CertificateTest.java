package com.example.solana.certbadgemanager.model;


import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CertificateTest {

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(Certificate.class).verify();
    }

    @Test
    void testSettersAndGetters() {
        Certificate certificate = new Certificate();
        certificate.setId(String.valueOf(1L));
        certificate.setTitle("Completion Certificate");
        certificate.setAwardedTo("2024-11-15");

        assertEquals(1L, certificate.getId());
        assertEquals("Completion Certificate", certificate.getTitle());
        assertEquals("John Doe", certificate.getAwardedTo());
    }

    @Test
    void testToString() {
        Certificate certificate = new Certificate();
        certificate.setId(String.valueOf(1L));
        certificate.setTitle("Completion Certificate");

        String expected = "Certificate{id=1, title='Completion Certificate'}";
        assertEquals(expected, certificate.toString());
    }
}
