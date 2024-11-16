package com.example.solana.certbadgemanager.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BadgeNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Badge not found";
        BadgeNotFoundException exception = new BadgeNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionWithCause() {
        String errorMessage = "Badge not found";
        Throwable cause = new RuntimeException("Cause of the error");
        BadgeNotFoundException exception = new BadgeNotFoundException(errorMessage, cause);

        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
