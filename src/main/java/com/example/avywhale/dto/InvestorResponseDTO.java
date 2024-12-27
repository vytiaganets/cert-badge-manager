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
public class InvestorResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private BigDecimal investmentAmount;
    private String description;

}
