package com.example.avywhale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvestmentResponseDTO {
    private Long id;
    private Double amount;
    private String assetType;
    private LocalDate investmentDate;
    private Long investorId; }
