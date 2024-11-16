package com.example.solana.certbadgemanager.util;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class SolanaConnectionChecker {
    public static void main(String[] args){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8899";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonBody = "{\"jsonrpc\":\"2.0\", \"id\":1, \"method\":\"getHealth\"}";

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        try{
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            System.out.println("Response from Solana node: " + response.getBody());
        } catch (Exception e) {
            System.out.println("Failed to connect to Solana node: " + e.getMessage());
        }
    }
}
