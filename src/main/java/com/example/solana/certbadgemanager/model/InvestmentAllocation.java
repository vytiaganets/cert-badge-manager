package com.example.solana.certbadgemanager.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "investment_allocations")
public class InvestmentAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "investment_amount", nullable = false)
    private BigDecimal investmentAmount;

    @Column(name = "allocation_percentage", nullable = false)
    private BigDecimal allocationPercentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "investor_id", nullable = false)
    private Investor investor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tokenomics_id", nullable = false)
    private Tokenomics tokenomics;

    public InvestmentAllocation() {
    }

    public InvestmentAllocation(BigDecimal investmentAmount, BigDecimal allocationPercentage, Investor investor, Tokenomics tokenomics) {
        this.investmentAmount = investmentAmount;
        this.allocationPercentage = allocationPercentage;
        this.investor = investor;
        this.tokenomics = tokenomics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(BigDecimal investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public BigDecimal getAllocationPercentage() {
        return allocationPercentage;
    }

    public void setAllocationPercentage(BigDecimal allocationPercentage) {
        this.allocationPercentage = allocationPercentage;
    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Tokenomics getTokenomics() {
        return tokenomics;
    }

    public void setTokenomics(Tokenomics tokenomics) {
        this.tokenomics = tokenomics;
    }
}
