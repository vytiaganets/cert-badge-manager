package com.example.solana.certbadgemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.IdGenerator;

import java.util.UUID;

@Configuration
public class ApplicationConfig {

    @Bean
    public IdGenerator idGenerator(){
        return new IdGenerator() {
            @Override
            public UUID generateId() {
                return null;
            }
        };
    }
}
