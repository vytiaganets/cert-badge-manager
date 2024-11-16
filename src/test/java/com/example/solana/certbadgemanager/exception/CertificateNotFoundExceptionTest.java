package com.example.solana.certbadgemanager.exception;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CertificateNotFoundExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Certificate not found";
        CertificateNotFoundException exception = new CertificateNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    void testExceptionWithCause() {
        String errorMessage = "Certificate not found";
        Throwable cause = new RuntimeException("Cause of the error");
        CertificateNotFoundException exception = new CertificateNotFoundException(errorMessage, cause);

        assertEquals(errorMessage, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}
