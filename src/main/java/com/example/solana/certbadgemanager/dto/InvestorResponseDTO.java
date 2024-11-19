package com.example.solana.certbadgemanager.dto;

import java.math.BigDecimal;

public class InvestorResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private BigDecimal investmentAmount;
    private String description;

    public InvestorResponseDTO(Long id, String name, String email, String phoneNumber,
                               BigDecimal investmentAmount, String description) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.investmentAmount = investmentAmount;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BigDecimal getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(BigDecimal investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
