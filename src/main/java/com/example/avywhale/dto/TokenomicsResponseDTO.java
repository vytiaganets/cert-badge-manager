package com.example.avywhale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenomicsResponseDTO {

    private Long id;

    private String tokenName;

    private BigDecimal totalSupply;

    private BigDecimal circulatingSupply;

    private BigDecimal reservedForRewards;

    private BigDecimal transactionFeePercentage;

    private String description;

}
