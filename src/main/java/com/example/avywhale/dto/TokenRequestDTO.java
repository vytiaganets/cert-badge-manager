package com.example.avywhale.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenRequestDTO {

    @NotBlank(message = "Token name cannot be blank.")
    private String tokenName;

    @NotNull(message = "Total supply cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total supply must be greater than 0.")
    private BigDecimal totalSupply;

    @NotNull(message = "Circulating supply cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Circulating supply must be greater than 0.")
    private BigDecimal circulatingSupply;

    @NotNull(message = "Reserved for rewards cannot be null.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Reserved for rewards must be 0 or greater.")
    private BigDecimal reservedForRewards;

    @NotNull(message = "Transaction fee percentage cannot be null.")
    @DecimalMin(value = "0.0", inclusive = true, message = "Transaction fee percentage must be 0 or greater.")
    private BigDecimal transactionFeePercentage;

    private String description;

}
