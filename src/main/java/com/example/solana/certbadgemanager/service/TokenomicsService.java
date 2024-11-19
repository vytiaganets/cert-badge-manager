package com.example.solana.certbadgemanager.service;

import com.example.solana.certbadgemanager.dto.TokenomicsResponseDTO;
import com.example.solana.certbadgemanager.model.Tokenomics;
import com.example.solana.certbadgemanager.dto.TokenomicsRequestDTO;
import com.example.solana.certbadgemanager.dto.TokenomicsResponseDTO;
import com.example.solana.certbadgemanager.model.Tokenomics;
import com.example.solana.certbadgemanager.repository.TokenomicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TokenomicsService {

    private final TokenomicsRepository tokenomicsRepository;

    @Autowired
    public TokenomicsService(TokenomicsRepository tokenomicsRepository) {
        this.tokenomicsRepository = tokenomicsRepository;
    }

    public TokenomicsResponseDTO createTokenomics(TokenomicsRequestDTO requestDTO) {
        Tokenomics tokenomics = new Tokenomics();
        tokenomics.setTokenName(requestDTO.getTokenName());
        tokenomics.setTotalSupply(requestDTO.getTotalSupply());
        tokenomics.setCirculatingSupply(requestDTO.getCirculatingSupply());
        tokenomics.setReservedForRewards(requestDTO.getReservedForRewards());
        tokenomics.setTransactionFeePercentage(requestDTO.getTransactionFeePercentage());
        tokenomics.setDescription(requestDTO.getDescription());

        Tokenomics savedTokenomics = tokenomicsRepository.save(tokenomics);

        return mapToResponseDTO(savedTokenomics);
    }

    public TokenomicsResponseDTO getTokenomicsById(Long id) {
        Tokenomics tokenomics = tokenomicsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tokenomics not found with id: " + id));
        return mapToResponseDTO(tokenomics);
    }

    public List<TokenomicsResponseDTO> getAllTokenomics() {
        return tokenomicsRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public TokenomicsResponseDTO updateTokenomics(Long id, TokenomicsRequestDTO requestDTO) {
        Tokenomics tokenomics = tokenomicsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tokenomics not found with id: " + id));

        tokenomics.setTokenName(requestDTO.getTokenName());
        tokenomics.setTotalSupply(requestDTO.getTotalSupply());
        tokenomics.setCirculatingSupply(requestDTO.getCirculatingSupply());
        tokenomics.setReservedForRewards(requestDTO.getReservedForRewards());
        tokenomics.setTransactionFeePercentage(requestDTO.getTransactionFeePercentage());
        tokenomics.setDescription(requestDTO.getDescription());

        Tokenomics updatedTokenomics = tokenomicsRepository.save(tokenomics);

        return mapToResponseDTO(updatedTokenomics);
    }

    public void deleteTokenomics(Long id) {
        if (!tokenomicsRepository.existsById(id)) {
            throw new RuntimeException("Tokenomics not found with id: " + id);
        }
        tokenomicsRepository.deleteById(id);
    }

    private TokenomicsResponseDTO mapToResponseDTO(Tokenomics tokenomics) {
        TokenomicsResponseDTO responseDTO = new TokenomicsResponseDTO();
        responseDTO.setId(tokenomics.getId());
        responseDTO.setTokenName(tokenomics.getTokenName());
        responseDTO.setTotalSupply(tokenomics.getTotalSupply());
        responseDTO.setCirculatingSupply(tokenomics.getCirculatingSupply());
        responseDTO.setReservedForRewards(tokenomics.getReservedForRewards());
        responseDTO.setTransactionFeePercentage(tokenomics.getTransactionFeePercentage());
        responseDTO.setDescription(tokenomics.getDescription());
        return responseDTO;
    }
}
