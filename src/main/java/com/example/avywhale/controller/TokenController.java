package com.example.avywhale.controller;

import com.example.avywhale.blockchain.SolanaClient;
import com.example.avywhale.dto.TokenRequestDTO;
import com.example.avywhale.model.Token;
import com.example.avywhale.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createToken(@Valid @RequestBody TokenRequestDTO tokenRequestDTO) {
        try {
            Token newToken = new Token();
            newToken.setBlockchain("Solana");
            newToken.setName(tokenRequestDTO.getTokenName());
            newToken.setSymbol(tokenRequestDTO.getTokenName().substring(0, Math.min(10, tokenRequestDTO.getTokenName().length())));
            newToken.setTotalSupply(tokenRequestDTO.getTotalSupply());
            newToken.setCirculatingSupply(tokenRequestDTO.getCirculatingSupply());
            newToken.setCreationDate(LocalDateTime.now());
            newToken.setDescription(tokenRequestDTO.getDescription());

            tokenService.saveToken(newToken);

            return ResponseEntity.ok("Token created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating token: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<Token>> getTokens() {
        try {
            List<Token> tokens = tokenService.getAllTokensWithBalances();
            return ResponseEntity.ok(tokens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
