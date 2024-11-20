package com.example.solana.certbadgemanager.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;

public class IntegrationHelper {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String apiKey;
    public IntegrationHelper(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.restTemplate = new RestTemplate();
    }
    @Autowired
    public IntegrationHelper(RestTemplate restTemplate, String externalServiceBaseUrl, String externalServiceApiKey) {
        this.restTemplate = restTemplate;
        this.baseUrl = externalServiceBaseUrl;
        this.apiKey = externalServiceApiKey;
    }

    public String fetchExternalData(String endpoint) {
        String url = baseUrl + endpoint + "?apiKey=" + apiKey;
        return restTemplate.getForObject(url, String.class);
    }
    public String sendGetRequest(String apiUrl) {
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                String.class
        );
        return response.getBody();
    }

    public String sendPostRequest(String apiUrl, String payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,
                entity,
                String.class
        );
        return response.getBody();
    }

    public boolean isResponseValid(String response) {
        return response != null && !response.isEmpty() && response.contains("success");
    }
}
