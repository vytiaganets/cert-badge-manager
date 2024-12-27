package com.example.avywhale.controller;

import com.example.avywhale.config.TokenomicsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tokenomics")
public class TokenomicsController {

    private final TokenomicsConfig tokenomicsConfig;

    @Autowired
    public TokenomicsController(TokenomicsConfig tokenomicsConfig) {
        this.tokenomicsConfig = tokenomicsConfig;
    }

    @GetMapping("/total-supply")
    public long getTotalSupply() {
        return tokenomicsConfig.getTotalSupply();
    }

    @GetMapping("/team-allocation")
    public double getTeamAllocation() {
        return tokenomicsConfig.getTeamAllocation();
    }

    @GetMapping("/community-allocation")
    public double getCommunityAllocation() {
        return tokenomicsConfig.getCommunityAllocation();
    }

    @GetMapping("/investor-allocation")
    public double getInvestorAllocation() {
        return tokenomicsConfig.getInvestorAllocation();
    }

    @GetMapping("/reserved-funds")
    public double getReservedFunds() {
        return tokenomicsConfig.getReservedFunds();
    }

    @GetMapping
    public TokenomicsConfig getTokenomicsDetails() {
        return tokenomicsConfig;
    }
}
