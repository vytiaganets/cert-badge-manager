package com.example.avywhale.service;

import com.example.avywhale.model.Token;
import com.example.avywhale.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token saveToken(Token token) {
        return tokenRepository.save(token);
    }

    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }

    public List<Token> getAllTokensWithBalances() {
        List<Token> tokens = tokenRepository.findAll();
        for (Token token : tokens) {
                        BigDecimal balance = token.getCirculatingSupply().multiply(BigDecimal.valueOf(0.1));
            token.setBalance(balance);
        }
        return tokens;
    }
}