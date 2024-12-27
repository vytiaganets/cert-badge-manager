package com.example.avywhale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvestmentAllocationResponseDTO {

    private Long allocationId;
    private Long investorId;
    private String investorName;
    private String tokenName;
    private Double allocatedAmount;

}