package com.example.avywhale.blockchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TokenDistributionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenDistributionService.class);

    private final SolanaClient solanaClient;

    public TokenDistributionService(SolanaClient solanaClient) {
        this.solanaClient = solanaClient;
    }


    public String[] distributeTokens(String senderPrivateKey, String[] recipientAddresses, long[] amounts) {
        if (recipientAddresses.length != amounts.length) {
            throw new IllegalArgumentException("Recipient addresses and amounts must have the same length.");
        }

        LOGGER.info("Starting token distribution to {} recipients", recipientAddresses.length);
        String[] transactionIds = new String[recipientAddresses.length];

        for (int i = 0; i < recipientAddresses.length; i++) {
            String recipientAddress = recipientAddresses[i];
            long amount = amounts[i];
            LOGGER.info("Distributing {} tokens to recipient: {}", amount, recipientAddress);
            try {
                String transactionId = solanaClient.transfer(senderPrivateKey, recipientAddress, amount);
                transactionIds[i] = transactionId;
                LOGGER.info("Successfully distributed to {}. Transaction ID: {}", recipientAddress, transactionId);
            } catch (Exception e) {
                LOGGER.error("Error distributing tokens to recipient {}: ", recipientAddress, e);
                throw new RuntimeException("Token distribution failed for recipient: " + recipientAddress, e);
            }
        }

        return transactionIds;
    }


    public long checkWalletBalance(String walletAddress) {
        LOGGER.info("Checking balance for wallet: {}", walletAddress);
        try {
            long balance = solanaClient.getBalance(walletAddress);
            LOGGER.info("Wallet balance: {}", balance);
            return balance;
        } catch (Exception e) {
            LOGGER.error("Error occurred while checking wallet balance: ", e);
            throw new RuntimeException("Failed to check wallet balance", e);
        }
    }
}
