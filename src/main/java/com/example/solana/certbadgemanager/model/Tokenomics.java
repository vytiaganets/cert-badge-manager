package com.example.solana.certbadgemanager.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tokenomics")
public class Tokenomics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tokenName;

    @Column(nullable = false)
    private BigDecimal totalSupply;

    @Column(nullable = false)
    private BigDecimal circulatingSupply;

    @Column(nullable = false)
    private BigDecimal reservedForRewards;

    @Column(nullable = false)
    private BigDecimal transactionFeePercentage;

    @Column(length = 500)
    private String description;

    public Tokenomics() {
        // Default constructor
    }

    public Tokenomics(String tokenName, BigDecimal totalSupply, BigDecimal circulatingSupply,
                      BigDecimal reservedForRewards, BigDecimal transactionFeePercentage, String description) {
        this.tokenName = tokenName;
        this.totalSupply = totalSupply;
        this.circulatingSupply = circulatingSupply;
        this.reservedForRewards = reservedForRewards;
        this.transactionFeePercentage = transactionFeePercentage;
        this.description = description;
    }

    // Getters and Setters

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

    @Override
    public String toString() {
        return "Tokenomics{" +
                "id=" + id +
                ", tokenName='" + tokenName + '\'' +
                ", totalSupply=" + totalSupply +
                ", circulatingSupply=" + circulatingSupply +
                ", reservedForRewards=" + reservedForRewards +
                ", transactionFeePercentage=" + transactionFeePercentage +
                ", description='" + description + '\'' +
                '}';
    }
}
