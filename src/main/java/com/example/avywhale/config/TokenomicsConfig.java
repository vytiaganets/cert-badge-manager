package com.example.avywhale.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenomicsConfig {

    @Value("${tokenomics.totalSupply}")
    private long totalSupply;

    @Value("${tokenomics.teamAllocation}")
    private double teamAllocation;

    @Value("${tokenomics.communityAllocation}")
    private double communityAllocation;

    @Value("${tokenomics.investorAllocation}")
    private double investorAllocation;

    @Value("${tokenomics.reservedFunds}")
    private double reservedFunds;

    public long getTotalSupply() {
        return totalSupply;
    }

    public double getTeamAllocation() {
        return teamAllocation;
    }

    public double getCommunityAllocation() {
        return communityAllocation;
    }

    public double getInvestorAllocation() {
        return investorAllocation;
    }

    public double getReservedFunds() {
        return reservedFunds;
    }

    @Override
    public String toString() {
        return "TokenomicsConfig{" +
                "totalSupply=" + totalSupply +
                ", teamAllocation=" + teamAllocation +
                ", communityAllocation=" + communityAllocation +
                ", investorAllocation=" + investorAllocation +
                ", reservedFunds=" + reservedFunds +
                '}';
    }
}
