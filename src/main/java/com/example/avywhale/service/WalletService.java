package com.example.avywhale.service;

import com.example.avywhale.dto.WalletResponseDTO;
import com.example.avywhale.exception.TokenomicsException;
import com.example.avywhale.model.Wallet;
import com.example.avywhale.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
public class WalletService {

    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }


    public BigDecimal getWalletBalance(String userId) {
        Wallet wallet = findWalletByUserId(userId);
        return wallet.getBalance();
    }


    public WalletResponseDTO updateWalletBalance(String userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByPlayerId(userId)
                .orElseThrow(() -> new TokenomicsException("Wallet not found for user ID: " + userId));
        BigDecimal newBalance = wallet.getBalance().add(amount);

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new TokenomicsException("Insufficient funds in wallet for user ID: " + userId);
        }

        wallet.setBalance(newBalance);
        Wallet updatedWallet = walletRepository.save(wallet);

        return new WalletResponseDTO(updatedWallet.getPlayerId(), updatedWallet.getBalance());
    }


    public WalletResponseDTO createWallet(String userId) {
        if (walletRepository.existsByPlayerId(userId)) {
            throw new TokenomicsException("Wallet already exists for user ID: " + userId);
        }

        Wallet wallet = new Wallet();
        wallet.setPlayerId(userId);
        wallet.setBalance(BigDecimal.ZERO);

        Wallet savedWallet = walletRepository.save(wallet);

        return new WalletResponseDTO(savedWallet.getPlayerId(), savedWallet.getBalance());
    }


    private Wallet findWalletByUserId(String userId) {
        Optional<Wallet> optionalWallet = walletRepository.findByPlayerId(userId);
        return optionalWallet.orElseThrow(() -> new TokenomicsException("Wallet not found for user ID: " + userId));
    }
}
