package com.example.solana.certbadgemanager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TokenRequestDTO {

    @NotBlank(message = "Token name cannot be blank.")
    private String tokenName;

    @NotNull(message = "Total supply cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total supply must be greater than 0.")
    private BigDecimal totalSupply;

    @NotNull(message = "Circulating supply cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Circulating supply must be greater than 0.")
    private BigDecimal circulatingSupply;

    @NotNull(message = "Reserved for rewards cannot be null.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Reserved for rewards must be 0 or greater.")
    private BigDecimal reservedForRewards;

    @NotNull(message = "Transaction fee percentage cannot be null.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Transaction fee percentage must be 0 or greater.")
    private BigDecimal transactionFeePercentage;

    private String description;

    // Getters and Setters
    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public BigDecimal getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(BigDecimal totalSupply) {
        this.totalSupply = totalSupply;
    }

    public BigDecimal getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(BigDecimal circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public BigDecimal getReservedForRewards() {
        return reservedForRewards;
    }

    public void setReservedForRewards(BigDecimal reservedForRewards) {
        this.reservedForRewards = reservedForRewards;
    }

    public BigDecimal getTransactionFeePercentage() {
        return transactionFeePercentage;
    }

    public void setTransactionFeePercentage(BigDecimal transactionFeePercentage) {
        this.transactionFeePercentage = transactionFeePercentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
