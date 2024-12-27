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
public class InvestmentRequestDTO {
    private String investorName;
    private BigDecimal amount;
    private String investmentDate;
    private String status;

}
