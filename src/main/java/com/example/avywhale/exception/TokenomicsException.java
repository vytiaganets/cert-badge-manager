package com.example.avywhale.exception;


public class TokenomicsException extends RuntimeException {


    public TokenomicsException(String message) {
        super(message);
    }


    public TokenomicsException(String message, Throwable cause) {
        super(message, cause);
    }
}
