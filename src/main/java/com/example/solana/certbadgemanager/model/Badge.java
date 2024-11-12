package com.example.solana.certbadgemanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Badge {
    private String id;
    private String name;
    private String description;
    private String awardedTo;
    private LocalDateTime awardedDate;
    private String blockchainTransactionId;
}