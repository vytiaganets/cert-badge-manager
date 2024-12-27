package com.example.avywhale.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvestmentAllocationRequestDTO {

    @NotNull(message = "Investor ID must not be null")
    private Long investorId;

    @NotBlank(message = "Token name must not be blank")
    private String tokenName;

    @NotNull(message = "Allocated amount must not be null")
    @Positive(message = "Allocated amount must be positive")
    private Double allocatedAmount;

}