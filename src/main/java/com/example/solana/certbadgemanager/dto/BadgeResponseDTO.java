package com.example.solana.certbadgemanager.dto;

import lombok.Data;

@Data
public class BadgeResponseDTO {
    private String id;
    private String name;
    private String description;
    private String userId;
    private String blockchainTransactionId;
    private String status;

    public BadgeResponseDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
