package com.example.avywhale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.IdGenerator;
import org.springframework.web.client.RestTemplate;

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
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
