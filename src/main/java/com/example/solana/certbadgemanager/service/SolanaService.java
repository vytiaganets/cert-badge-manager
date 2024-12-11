package com.example.solana.certbadgemanager.service;

import com.example.solana.certbadgemanager.blockchain.SolanaClient;
import com.example.solana.certbadgemanager.util.LamportsToTokensConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class SolanaService {
    private static final double TOKEN_RATE_IN_SOL = 0.1;

    public boolean invest(String walletAddress, double amount) {
       try {

            System.out.println("Investing " + amount + " coins to " + walletAddress);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getWalletBalance(String walletAddress) {
        try {

            long lamports = getBalanceInLamports(walletAddress);

            double tokens = LamportsToTokensConverter.convertLamportsToTokens(lamports, TOKEN_RATE_IN_SOL);

            return String.format("%.2f", tokens);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch wallet balance", e);
        }
    }

    private long getBalanceInLamports(String walletAddress) {
        try {
            String rpcUrl = "https://api.devnet.solana.com";

            String requestPayload = String.format(
                    "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"getBalance\", \"params\": [\"%s\"] }",
                    walletAddress
            );

            System.out.println("Sending request: " + requestPayload);

            HttpURLConnection connection = (HttpURLConnection) new URL(rpcUrl).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.getOutputStream().write(requestPayload.getBytes());

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            String response = responseBuilder.toString();
            System.out.println("Response: " + response);

            JsonNode rootNode = new ObjectMapper().readTree(response);
            if (rootNode.has("error")) {
                throw new RuntimeException("API Error: " + rootNode.get("error").toString());
            }
            if (rootNode.has("result") && rootNode.get("result").has("value")) {
                return rootNode.get("result").get("value").asLong();
            } else {
                throw new RuntimeException("Invalid API response: " + rootNode.toString());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch wallet balance", e);
        }
    }

    @Retryable(
            value = {IOException.class, HttpRetryException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 5000)
    )
    public boolean topUpWallet(String walletAddress, long amount) {
        try {
            String rpcUrl = "https://api.devnet.solana.com";
            String requestPayload = String.format(
                    "{ \"jsonrpc\": \"2.0\", \"id\": 1, \"method\": \"requestAirdrop\", \"params\": [\"%s\", %d] }",
                    walletAddress, amount
            );

            HttpURLConnection connection = (HttpURLConnection) new URL(rpcUrl).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.getOutputStream().write(requestPayload.getBytes());

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            String response = responseBuilder.toString();
            System.out.println("Transaction Response: " + response);

            return response.contains("result");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public String spendFromWallet(String walletAddress, long amount) {

        long currentBalance = Long.parseLong(getWalletBalance(walletAddress));
        if (currentBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        return "Spent " + amount + " tokens from wallet " + walletAddress;
    }

    public String transferTokens(String fromWallet, String toWallet, long amount) {

        long currentBalance = Long.parseLong(getWalletBalance(fromWallet));
        if (currentBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        return "Transferred " + amount + " tokens from " + fromWallet + " to " + toWallet;
    }

}