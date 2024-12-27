package com.example.avywhale.exception;

public class BadgeNotFoundException extends RuntimeException{
    public BadgeNotFoundException(String message){
        super(message);
    }
}
