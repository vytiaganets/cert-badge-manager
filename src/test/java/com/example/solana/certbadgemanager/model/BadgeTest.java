package com.example.solana.certbadgemanager.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadgeTest {

    @Test
    void testEqualsAndHashCode() {
        EqualsVerifier.simple().forClass(Badge.class).verify();
    }

    @Test
    void testSettersAndGetters() {
        Badge badge = new Badge();
        badge.setId(1L);
        badge.setName("Achievement Badge");
        badge.setDescription("Awarded for exceptional achievements");

        assertEquals(1L, badge.getId());
        assertEquals("Achievement Badge", badge.getName());
        assertEquals("Awarded for exceptional achievements", badge.getDescription());
    }

    @Test
    void testToString() {
        Badge badge = new Badge();
        badge.setId(1L);
        badge.setName("Achievement Badge");

        String expected = "Badge{id=1, name='Achievement Badge'}";
        assertEquals(expected, badge.toString());
    }
}
