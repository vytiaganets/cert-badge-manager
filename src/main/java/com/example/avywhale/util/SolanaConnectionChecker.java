package com.example.avywhale.util;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SolanaConnectionChecker implements CommandLineRunner {

    @Value("${solana.rpc.url}")
    private String rpcUrl;

    public void checkConnection() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String payload = new JSONObject()
                .put("jsonrpc", "2.0")
                .put("id", 1)
                .put("method", "getLatestBlockhash")
                .toString();

        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        try {
            String response = restTemplate.postForObject(rpcUrl, request, String.class);
            System.out.println("Connected to Solana. Response: " + response);
        } catch (Exception e) {
            System.err.println("Failed to connect to Solana: " + e.getMessage());
        }
    }

    @Override
    public void run(String... args) {
        checkConnection();
    }

    public String getLatestBlockhash() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String payload = new JSONObject()
                .put("jsonrpc", "2.0")
                .put("id", 1)
                .put("method", "getLatestBlockhash")
                .toString();

        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        try {
            return restTemplate.postForObject(rpcUrl, request, String.class);
        } catch (Exception e) {
            System.err.println("Failed to connect to Solana: " + e.getMessage());
            return null;
        }
    }
}
