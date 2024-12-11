package com.example.solana.certbadgemanager.controller;

import com.example.solana.certbadgemanager.blockchain.SolanaClient;
import com.example.solana.certbadgemanager.dto.TokenRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    private final SolanaClient solanaClient;

    public TokenController(SolanaClient solanaClient) {
        this.solanaClient = solanaClient;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createToken(@Valid @RequestBody TokenRequestDTO tokenRequestDTO) {
        try {
            String transactionResult = solanaClient.createToken(tokenRequestDTO);
            return ResponseEntity.ok("Token created successfully. Transaction ID: " + transactionResult);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating token: " + e.getMessage());
        }
    }
}
