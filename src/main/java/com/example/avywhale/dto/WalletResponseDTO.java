package com.example.avywhale.dto;

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
public class WalletResponseDTO {

    private String playerId;
    private BigDecimal balance;
    private String currency;
    private LocalDateTime lastUpdated;

    public WalletResponseDTO(String playerId, BigDecimal balance) {
        this.playerId = playerId;
        this.balance = balance;
    }

        @Override
    public String toString() {
        return "WalletResponseDTO{" +
                "playerId='" + playerId + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
