package com.example.avywhale.exception;

public class TokenAllocationException extends RuntimeException {

    public TokenAllocationException(String message) {
        super(message);
    }

    public TokenAllocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
