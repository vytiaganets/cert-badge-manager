package com.example.avywhale.exception;


public class NFTException extends RuntimeException {


    public NFTException(String message) {
        super(message);
    }


    public NFTException(String message, Throwable cause) {
        super(message, cause);
    }
}
