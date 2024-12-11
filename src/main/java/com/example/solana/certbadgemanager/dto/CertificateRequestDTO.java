package com.example.solana.certbadgemanager.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CertificateRequestDTO {
    private String title;
    private String description;
}
