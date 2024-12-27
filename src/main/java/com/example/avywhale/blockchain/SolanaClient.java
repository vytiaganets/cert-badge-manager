package com.example.avywhale.blockchain;

import com.example.avywhale.dto.TokenRequestDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class SolanaClient {

    private final String solanaRpcUrl;

    public SolanaClient(@Value("${solana.rpc.url}") String solanaRpcUrl) {
        this.solanaRpcUrl = solanaRpcUrl != null ? solanaRpcUrl : "https://default-solana-rpc-url.com";
    }

    public String transfer(String senderPrivateKey, String recipientAddress, long amount) {
        try {
            String requestPayload = String.format(
                    "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"sendTransaction\", \"params\": [\"%s\", \"%s\", %d] }",
                    senderPrivateKey, recipientAddress, amount
            );

            return sendTransaction(requestPayload);
        } catch (Exception e) {
            throw new RuntimeException("Transfer failed", e);
        }
    }

    public long getBalance(String walletAddress) {
        try {
            String requestPayload = String.format(
                    "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getBalance\", \"params\": [\"%s\"] }",
                    walletAddress
            );

            String response = sendTransaction(requestPayload);
            JsonNode rootNode = new ObjectMapper().readTree(response);

            if (rootNode.has("result") && rootNode.get("result").has("value")) {
                return rootNode.get("result").get("value").asLong();
            } else {
                throw new RuntimeException("Failed to retrieve balance");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error getting wallet balance", e);
        }
    }

    public String mintNFT(String creatorPrivateKey, String metadataUri, String recipientAddress) {
        try {
            String requestPayload = String.format(
                    "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"mintNFT\", \"params\": [\"%s\", \"%s\", \"%s\"] }",
                    creatorPrivateKey, metadataUri, recipientAddress
            );

            return sendTransaction(requestPayload);
        } catch (Exception e) {
            throw new RuntimeException("NFT minting failed", e);
        }
    }

    public String getNFTMetadata(String nftAddress) {
        try {
            String requestPayload = String.format(
                    "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getAccountInfo\", \"params\": [\"%s\", {\"encoding\": \"jsonParsed\"}] }",
                    nftAddress
            );

            String response = sendTransaction(requestPayload);
            JsonNode rootNode = new ObjectMapper().readTree(response);

            if (rootNode.has("result") && rootNode.get("result").has("value")) {
                return rootNode.get("result").get("value").toString();
            } else {
                throw new RuntimeException("Failed to retrieve NFT metadata");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching NFT metadata", e);
        }
    }

    public String deploySmartContract(String creatorPrivateKey, byte[] contractCodeBase64) {
        try {
            String requestPayload = String.format(
                    "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"deployContract\", \"params\": [\"%s\", \"%s\"] }",
                    creatorPrivateKey, new String(contractCodeBase64)
            );

            return sendTransaction(requestPayload);
        } catch (Exception e) {
            throw new RuntimeException("Smart contract deployment failed", e);
        }
    }

    public String invokeSmartContract(String contractAddress, String methodName, String[] args) {
        try {
            String argsString = new ObjectMapper().writeValueAsString(args);
            String requestPayload = String.format(
                    "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"invokeContract\", \"params\": [\"%s\", \"%s\", %s] }",
                    contractAddress, methodName, argsString
            );

            return sendTransaction(requestPayload);
        } catch (Exception e) {
            throw new RuntimeException("Smart contract invocation failed", e);
        }
    }

    public String getSmartContractState(String contractAddress) {
        try {
            String requestPayload = String.format(
                    "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getAccountInfo\", \"params\": [\"%s\", {\"encoding\": \"jsonParsed\"}] }",
                    contractAddress
            );

            String response = sendTransaction(requestPayload);
            JsonNode rootNode = new ObjectMapper().readTree(response);

            if (rootNode.has("result") && rootNode.get("result").has("value")) {
                return rootNode.get("result").get("value").toString();
            } else {
                throw new RuntimeException("Failed to retrieve smart contract state");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching smart contract state", e);
        }
    }

    private String sendTransaction(String rawTransaction) {
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

    public String createToken(TokenRequestDTO tokenRequestDTO) {
        try {
            String creatorPrivateKey = "3Add3y8Kqok3ZbAdhmi6gR6kopykduDjsd8HGVcKWXuWu33JXjc25bj7CeuauGrqFwmuMufMLXTnujZgJuU5qXzK";
            String tokenName = tokenRequestDTO.getTokenName();
            String symbol = "SYM";
            int decimals = 18;
            long initialSupply = tokenRequestDTO.getTotalSupply().longValue();

            String requestPayload = String.format(
                    "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"createToken\", \"params\": [\"%s\", \"%s\", \"%s\", %d, %d] }",
                    creatorPrivateKey, tokenName, symbol, decimals, initialSupply
            );

            return sendTransaction(requestPayload);
        } catch (Exception e) {
            throw new RuntimeException("Token creation failed", e);
        }
    }
}
