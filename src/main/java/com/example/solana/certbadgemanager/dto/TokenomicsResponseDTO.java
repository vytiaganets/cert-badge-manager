package com.example.solana.certbadgemanager.dto;

import java.math.BigDecimal;

public class TokenomicsResponseDTO {

    private Long id;

    private String tokenName;

    private BigDecimal totalSupply;

    private BigDecimal circulatingSupply;

    private BigDecimal reservedForRewards;

    private BigDecimal transactionFeePercentage;

    private String description;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
