package com.example.avywhale.blockchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NFTMintingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NFTMintingService.class);

    private final SolanaClient solanaClient;

    public NFTMintingService(SolanaClient solanaClient) {
        this.solanaClient = solanaClient;
    }


    public String mintNFT(String creatorPrivateKey, String metadataUri, String recipientAddress) {
        LOGGER.info("Minting NFT for recipient: {} with metadata URI: {}", recipientAddress, metadataUri);
        try {
                        String transactionId = solanaClient.mintNFT(creatorPrivateKey, metadataUri, recipientAddress);
            LOGGER.info("NFT minted successfully. Transaction ID: {}", transactionId);
            return transactionId;
        } catch (Exception e) {
            LOGGER.error("Error occurred during NFT minting: ", e);
            throw new RuntimeException("NFT minting failed", e);
        }
    }


    public String getNFTMetadata(String nftAddress) {
        LOGGER.info("Fetching metadata for NFT with address: {}", nftAddress);
        try {
            String metadataUri = solanaClient.getNFTMetadata(nftAddress);
            LOGGER.info("NFT metadata URI: {}", metadataUri);
            return metadataUri;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching NFT metadata: ", e);
            throw new RuntimeException("Failed to fetch NFT metadata", e);
        }
    }
}
