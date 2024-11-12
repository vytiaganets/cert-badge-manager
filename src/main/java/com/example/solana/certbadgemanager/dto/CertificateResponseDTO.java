package com.example.solana.certbadgemanager.dto;

import lombok.Data;

@Data
public class CertificateResponseDTO {
private String id;
private String title;
private String description;
private String userId;
private String blockchainTransactionId;
private String status;

    public CertificateResponseDTO(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
