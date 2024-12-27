package com.example.avywhale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
