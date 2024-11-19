package com.example.solana.certbadgemanager.dto;

import lombok.Data;

@Data
public class TokenomicsResponseDTO {

    private Long id;

    private String tokenName;

    private Double totalSupply;

    private Double circulatingSupply;

    private Double reservedForRewards;

    private Double transactionFeePercentage;

    private String description;
}
