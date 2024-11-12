package com.example.solana.certbadgemanager.exception;

public class CertificateNotFoundException extends RuntimeException{
    public CertificateNotFoundException(String message){
        super(message);
    }
}
