package com.example.solana.certbadgemanager.blockchain;

import com.example.solana.certbadgemanager.dto.BadgeRequestDTO;
import com.example.solana.certbadgemanager.dto.CertificateRequestDTO;
import com.example.solana.certbadgemanager.dto.TokenRequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public String getWalletBalance(String walletAddress) {
        try {
            System.out.println("Fetching balance for wallet: " + walletAddress);

            SolanaClient client = new SolanaClient("https://api.devnet.solana.com");
            String requestPayload = String.format(
                    "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getBalance\", \"params\": [\"%s\"] }",
                    walletAddress
            );

            System.out.println("Request payload: " + requestPayload);

            String response = client.sendTransaction(requestPayload);

            System.out.println("Response from Solana RPC: " + response);

            JsonNode rootNode = new ObjectMapper().readTree(response);

            if (rootNode.has("result") && rootNode.get("result").has("value")) {
                long balance = rootNode.get("result").get("value").asLong();
                System.out.println("Wallet balance (lamports): " + balance);
                return String.valueOf(balance);
            } else if (rootNode.has("error")) {
                System.err.println("Error from Solana RPC: " + rootNode.get("error").toString());
                throw new RuntimeException("Solana RPC error: " + rootNode.get("error").toString());
            } else {
                System.err.println("Unexpected response: " + rootNode.toString());
                throw new RuntimeException("Unexpected response: " + rootNode.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get wallet balance", e);
        }
    }



    public String createToken(TokenRequestDTO tokenRequestDTO) {
        if (tokenRequestDTO == null) {
            throw new IllegalArgumentException("TokenRequestDTO cannot be null");
        }


        String requestPayload = String.format(
                "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"sendTransaction\", " +
                        "\"params\": [{ \"tokenName\": \"%s\", \"totalSupply\": %s, " +
                        "\"circulatingSupply\": %s, \"reservedForRewards\": %s, \"transactionFeePercentage\": %s, " +
                        "\"description\": \"%s\" }] }",
                tokenRequestDTO.getTokenName(),
                tokenRequestDTO.getTotalSupply(),
                tokenRequestDTO.getCirculatingSupply(),
                tokenRequestDTO.getReservedForRewards(),
                tokenRequestDTO.getTransactionFeePercentage(),
                tokenRequestDTO.getDescription()
        );

        return sendTransaction(requestPayload);
    }

    public String createTokenCli(String mintAuthority, String freezeAuthority) throws IOException {
        String command = String.format("spl-token create-token --mint-authority %s --freeze-authority %s", mintAuthority, freezeAuthority);

        Process process = Runtime.getRuntime().exec(command);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            return output.toString();
        }
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
            System.out.println("RPC Response: " + response.body());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Transaction failed: " + response.body());
            }

            JsonNode rootNode = new ObjectMapper().readTree(response.body());
            if (rootNode.has("error")) {
                throw new RuntimeException("RPC Error: " + rootNode.get("error").toString());
            }
            if (!rootNode.has("result") || rootNode.get("result").isNull()) {
                throw new RuntimeException("Unexpected RPC Response: " + response.body());
            }
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
