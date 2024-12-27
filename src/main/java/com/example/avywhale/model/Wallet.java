package com.example.avywhale.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_id", nullable = false, unique = true)
    private String playerId;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

        @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", playerId='" + playerId + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
