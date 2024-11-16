package com.example.solana.certbadgemanager.blockchain;

import com.example.solana.certbadgemanager.dto.BadgeRequestDTO;
import com.example.solana.certbadgemanager.dto.CertificateRequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

public class SolanaClient {

    private final String solanaRpcUrl;

    public SolanaClient(String solanaRpcUrl) {
        this.solanaRpcUrl = solanaRpcUrl != null ? solanaRpcUrl : "http://api.mainnet-beta.solana.com";
    }


    public String sendTransaction(String rawTransaction) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(solanaRpcUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(rawTransaction))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Transaction failed: " + response.body());
            }

            JsonNode rootNode = new ObjectMapper().readTree(response.body());
            return rootNode.get("result").asText();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error sending transaction", e);
        }
    }


    public String getDataFromBlockchain(String accountId) {
        String requestPayload = String.format(
                "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getAccountInfo\", \"params\": [\"%s\", {\"encoding\": \"base64\"}] }",
                accountId
        );

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(solanaRpcUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestPayload))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Failed to fetch account data: " + response.body());
            }

            JsonNode rootNode = new ObjectMapper().readTree(response.body());
            return rootNode.get("result").get("value").get("data").get(0).asText();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error fetching data from blockchain", e);
        }
    }

    public String writeBadgeData(BadgeRequestDTO badgeRequest) {
        if (badgeRequest == null) {
            throw new IllegalArgumentException("Badge request cannot be null");
        }

        try {
            String badgeData = new ObjectMapper().writeValueAsString(badgeRequest);
            String base64Data = Base64.getEncoder().encodeToString(badgeData.getBytes());
            return sendTransaction(base64Data);
        } catch (IOException e) {
            throw new RuntimeException("Error writing badge data", e);
        }
    }

    public String writeCertificateData(CertificateRequestDTO certificateRequest) {
        if (certificateRequest == null) {
            throw new IllegalArgumentException("Certificate request cannot be null");
        }

        try {
            String certificateData = new ObjectMapper().writeValueAsString(certificateRequest);
            String base64Data = Base64.getEncoder().encodeToString(certificateData.getBytes());
            return sendTransaction(base64Data);
        } catch (IOException e) {
            throw new RuntimeException("Error writing certificate data", e);
        }
    }


    public String readBadgeData(String badgeId) {
        if (badgeId == null || badgeId.isEmpty()) {
            throw new IllegalArgumentException("Badge ID cannot be null or empty");
        }
        return getDataFromBlockchain(badgeId);
    }


    public String readCertificateData(String certificateId) {
        if (certificateId == null || certificateId.isEmpty()) {
            throw new IllegalArgumentException("Certificate ID cannot be null or empty");
        }
        return getDataFromBlockchain(certificateId);
    }


    public <T> String writeToBlockchain(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }

        try {
            String jsonData = new ObjectMapper().writeValueAsString(data);
            String base64Data = Base64.getEncoder().encodeToString(jsonData.getBytes());
            return sendTransaction(base64Data);
        } catch (IOException e) {
            throw new RuntimeException("Error writing data to blockchain", e);
        }
    }
}
