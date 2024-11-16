package com.example.solana.certbadgemanager.dto;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadgeDTOTest {

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(BadgeDTOTest.class).verify();
    }

    @Test
    void testSettersAndGetters() {
        BadgeResponseDTO badgeResponseDTO = new BadgeResponseDTO();
        badgeResponseDTO.setId(String.valueOf(1L));
        badgeResponseDTO.setName("Achievement Badge");
        badgeResponseDTO.setDescription("Awarded for exceptional achievements");

        assertEquals(1L, badgeResponseDTO.getId());
        assertEquals("Achievement Badge", badgeResponseDTO.getName());
        assertEquals("Awarded for exceptional achievements", badgeResponseDTO.getDescription());
    }

    @Test
    void testToString() {
        BadgeResponseDTO badgeResponseDTO = new BadgeResponseDTO();
        badgeResponseDTO.setId(String.valueOf(1L));
        badgeResponseDTO.setName("Achievement Badge");

        String expected = "BadgeDTO{id=1, name='Achievement Badge'}";
        assertEquals(expected, badgeResponseDTO.toString());
    }
}
