package com.example.avywhale.blockchain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WhiteBitClient {
    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String apiSecret;
    private final String baseUrl;

    public WhiteBitClient(@Value("${whitebit.api-key}") String apiKey,
                          @Value("${whitebit.api-secret}") String apiSecret,
                          @Value("${whitebit.base-url}") String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.baseUrl = baseUrl;
    }

    public ResponseEntity<String> getMarketData(String symbol) {
        String url = baseUrl + "/market/data?symbol=" + symbol;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
}
