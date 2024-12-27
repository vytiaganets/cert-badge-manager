package com.example.avywhale.controller;

import com.example.avywhale.service.WhiteBitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class WhiteBitController {

    private final WhiteBitService whiteBitService;

    public WhiteBitController(WhiteBitService whiteBitService) {
        this.whiteBitService = whiteBitService;
    }

    @GetMapping("/api/whitebit/market-data")
    public ResponseEntity<?> getMarketData(@RequestParam String market) {
        try {
            List<Map<String, Object>> marketData = whiteBitService.getMarketData();
            Map<String, Object> specificMarket = marketData.stream()
                    .filter(data -> market.equals(data.get("name")))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Market not found: " + market));
            return ResponseEntity.ok(specificMarket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

}