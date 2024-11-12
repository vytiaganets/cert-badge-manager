package com.example.solana.certbadgemanager.exception;

public class BadgeNotFoundException extends RuntimeException{
    public BadgeNotFoundException(String message){
        super(message);
    }
}
