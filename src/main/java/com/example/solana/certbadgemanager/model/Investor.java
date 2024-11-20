package com.example.solana.certbadgemanager.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "investors")
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InvestmentAllocation> investmentAllocations;

    public Investor() {
    }

    public Investor(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    public List<InvestmentAllocation> getInvestmentAllocations() {
        return investmentAllocations;
    }

    public void setInvestmentAllocations(List<InvestmentAllocation> investmentAllocations) {
        this.investmentAllocations = investmentAllocations;
    }

    public void setInvestmentAmount(BigDecimal investmentAmount) {
    }

    public void setDescription(String description) {
    }

    public BigDecimal getInvestmentAmount() {
        return null;
    }

    public String getDescription() {
        return null;
    }
}
