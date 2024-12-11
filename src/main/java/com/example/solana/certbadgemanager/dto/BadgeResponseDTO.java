package com.example.solana.certbadgemanager.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BadgeResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String awardedTo;
    private LocalDate awardedDate;
    private String issuer;
}
