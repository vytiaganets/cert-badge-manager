package com.example.avywhale.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TokenomicsRequestDTO {

    @NotBlank(message = "Token name is required.")
    private String tokenName;

    @NotNull(message = "Total supply must not be null.")
    @Positive(message = "Total supply must be a positive value.")
    private BigDecimal totalSupply;

    @NotNull(message = "Circulating supply must not be null.")
    @Positive(message = "Circulating supply must be a positive value.")
    private BigDecimal circulatingSupply;

    @NotNull(message = "Reserved for rewards must not be null.")
    @Positive(message = "Reserved for rewards must be a positive value.")
    private BigDecimal reservedForRewards;

    @NotNull(message = "Transaction fee percentage must not be null.")
    @Positive(message = "Transaction fee percentage must be a positive value.")
    private BigDecimal transactionFeePercentage;

    private String description;

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public @NotNull(message = "Total supply must not be null.") @Positive(message = "Total supply must be a positive value.") BigDecimal getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(BigDecimal totalSupply) {
        this.totalSupply = totalSupply;
    }

    public @NotNull(message = "Circulating supply must not be null.") @Positive(message = "Circulating supply must be a positive value.") BigDecimal getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(BigDecimal circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public @NotNull(message = "Reserved for rewards must not be null.") @Positive(message = "Reserved for rewards must be a positive value.") BigDecimal getReservedForRewards() {
        return reservedForRewards;
    }

    public void setReservedForRewards(BigDecimal reservedForRewards) {
        this.reservedForRewards = reservedForRewards;
    }

    public @NotNull(message = "Transaction fee percentage must not be null.") @Positive(message = "Transaction fee percentage must be a positive value.") BigDecimal getTransactionFeePercentage() {
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
