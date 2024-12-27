package com.example.avywhale.exception;

public class CertificateNotFoundException extends RuntimeException{
    public CertificateNotFoundException(String message){
        super(message);
    }
}
