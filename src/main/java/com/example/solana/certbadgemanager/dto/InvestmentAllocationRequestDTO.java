package com.example.solana.certbadgemanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InvestmentAllocationRequestDTO {

    @NotNull(message = "Investor ID must not be null")
    private Long investorId;

    @NotBlank(message = "Token name must not be blank")
    private String tokenName;

    @NotNull(message = "Allocated amount must not be null")
    @Positive(message = "Allocated amount must be positive")
    private Double allocatedAmount;

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public Double getAllocatedAmount() {
        return allocatedAmount;
    }

    public void setAllocatedAmount(Double allocatedAmount) {
        this.allocatedAmount = allocatedAmount;
    }
}