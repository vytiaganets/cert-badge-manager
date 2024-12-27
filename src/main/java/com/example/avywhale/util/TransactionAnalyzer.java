package com.example.avywhale.util;

import com.example.avywhale.model.Tokenomics;

import java.math.BigDecimal;
import java.util.List;

public class TransactionAnalyzer {

    public BigDecimal calculateTotalCirculatingValue(List<Tokenomics> tokenomicsList) {
        return tokenomicsList.stream()
                .map(token -> {
                    BigDecimal supply = token.getCirculatingSupply() != null ? token.getCirculatingSupply() : BigDecimal.ZERO;
                    BigDecimal feePercentage = token.getTransactionFeePercentage() != null ? token.getTransactionFeePercentage() : BigDecimal.ZERO;
                    return supply.multiply(feePercentage);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }



    public List<Tokenomics> filterByTotalSupply(List<Tokenomics> tokenomicsList, BigDecimal minSupply) {
        return tokenomicsList.stream()
                .filter(token -> token.getTotalSupply().compareTo(minSupply) >= 0)
                .toList();
    }

    public boolean isRewardSufficient(Tokenomics tokenomics, BigDecimal rewardThreshold) {
        return tokenomics.getReservedForRewards().compareTo(rewardThreshold) >= 0;
    }
}
