package com.example.solana.certbadgemanager.config;

import com.example.solana.certbadgemanager.blockchain.SolanaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class BlockchainConfig {
    @Bean
    public SolanaClient solanaClient(){
        String solanaRpcUrl = "https://api.mainnet-beta.solana.com";
        return new SolanaClient(solanaRpcUrl);
    }
}
