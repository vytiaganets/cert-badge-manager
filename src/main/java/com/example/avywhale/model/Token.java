package com.example.avywhale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String blockchain;  
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

    @Transient     private BigDecimal balance;

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
                ", balance=" + balance +                 '}';
    }
}
