package com.example.solana.certbadgemanager.model;

package com.example.tokenomics.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "symbol", nullable = false, unique = true, length = 10)
    private String symbol;

    @Column(name = "total_supply", nullable = false, precision = 20, scale = 6)
    private BigDecimal totalSupply;

    @Column(name = "circulating_supply", precision = 20, scale = 6)
    private BigDecimal circulatingSupply;

    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Column(name = "description", length = 500)
    private String description;

    public Token() {
    }

    public Token(String name, String symbol, BigDecimal totalSupply, BigDecimal circulatingSupply, String description) {
        this.name = name;
        this.symbol = symbol;
        this.totalSupply = totalSupply;
        this.circulatingSupply = circulatingSupply;
        this.creationDate = LocalDateTime.now();
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", totalSupply=" + totalSupply +
                ", circulatingSupply=" + circulatingSupply +
                ", creationDate=" + creationDate +
                ", description='" + description + '\'' +
                '}';
    }
}