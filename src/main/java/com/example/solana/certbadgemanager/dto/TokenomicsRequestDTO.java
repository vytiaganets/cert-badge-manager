package com.example.solana.certbadgemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class TokenomicsRequestDTO {

    @NotBlank(message = "Token name is required.")
    private String tokenName;

    @NotNull(message = "Total supply must not be null.")
    @Positive(message = "Total supply must be a positive value.")
    private Double totalSupply;

    @NotNull(message = "Circulating supply must not be null.")
    @Positive(message = "Circulating supply must be a positive value.")
    private Double circulatingSupply;

    @NotNull(message = "Reserved for rewards must not be null.")
    @Positive(message = "Reserved for rewards must be a positive value.")
    private Double reservedForRewards;

    @NotNull(message = "Transaction fee percentage must not be null.")
    @Positive(message = "Transaction fee percentage must be a positive value.")
    private Double transactionFeePercentage;

    private String description;

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public Double getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(Double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public Double getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(Double circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public Double getReservedForRewards() {
        return reservedForRewards;
    }

    public void setReservedForRewards(Double reservedForRewards) {
        this.reservedForRewards = reservedForRewards;
    }

    public Double getTransactionFeePercentage() {
        return transactionFeePercentage;
    }

    public void setTransactionFeePercentage(Double transactionFeePercentage) {
        this.transactionFeePercentage = transactionFeePercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
