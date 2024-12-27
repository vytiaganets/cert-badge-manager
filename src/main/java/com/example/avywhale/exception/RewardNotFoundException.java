package com.example.avywhale.exception;


public class RewardNotFoundException extends RuntimeException {


    public RewardNotFoundException(String message) {
        super(message);
    }


    public RewardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
