package com.example.solana.certbadgemanager.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BadgeResponseDTO {
    private String id;
    private String name;
    private String description;
    private String userId;
    private String blockchainTransactionId;
    private String status;
    private String issuedTo;
    private String issueDate;

    public BadgeResponseDTO(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
