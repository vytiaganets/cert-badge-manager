package com.example.solana.certbadgemanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;

public class InvestorRequestDTO {

    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @NotBlank(message = "Email cannot be blank.")
    @Email(message = "Email must be valid.")
    private String email;

    @NotBlank(message = "Phone number cannot be blank.")
    @Pattern(regexp = "\\+?\\d{10,15}", message = "Phone number must be valid.")
    private String phoneNumber;

    @NotNull(message = "Investment amount cannot be null.")
    private BigDecimal investmentAmount;

    private String description;

    // Getters and Setters
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
