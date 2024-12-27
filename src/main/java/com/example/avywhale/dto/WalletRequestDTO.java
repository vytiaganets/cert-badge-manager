package com.example.avywhale.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WalletRequestDTO {

    @NotNull(message = "Player ID cannot be null")
    private String playerId;

    @NotNull(message = "Amount cannot be null")
    @PositiveOrZero(message = "Amount must be zero or positive")
    private BigDecimal amount;

    @NotNull(message = "Currency cannot be null")
    private String currency;


    @Override
    public String toString() {
        return "WalletRequestDTO{" +
                "playerId='" + playerId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
