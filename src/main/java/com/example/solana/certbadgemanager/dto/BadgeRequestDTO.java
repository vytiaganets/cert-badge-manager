package com.example.solana.certbadgemanager.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BadgeRequestDTO {
    private String name;
    private String description;
    private String issuer;
    private String issuedTo;
}
