package com.example.avywhale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NFTResponseDTO {

    private String nftId;     private String name;     private String metadataUri;     private String ownerAddress;     private String transactionHash;     private String status;     private String message; 
        public NFTResponseDTO(Long nftId, String name, String metadataUri, String creatorAddress) {
        this.nftId = nftId.toString();
        this.name = name;
        this.metadataUri = metadataUri;
        this.ownerAddress = creatorAddress;
        this.transactionHash = "N/A";
        this.status = "ACTIVE";
        this.message = "NFT found";
    }

        public NFTResponseDTO(Long nftId, String name, String creatorAddress) {
        this.nftId = nftId.toString();
        this.name = name;
        this.ownerAddress = creatorAddress;
        this.transactionHash = "N/A";
        this.status = "ACTIVE";
        this.message = "NFT found";
    }
}
