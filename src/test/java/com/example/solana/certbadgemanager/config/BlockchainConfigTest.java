package com.example.solana.certbadgemanager.config;

import com.example.solana.certbadgemanager.blockchain.SolanaClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BlockchainConfigTest {

    @Autowired
    private SolanaClient solanaClient;

    @Test
    void solanaClient_ShouldBeInitialized() {
        assertNotNull(solanaClient, "SolanaClient should be initialized by BlockchainConfig");
    }
}
