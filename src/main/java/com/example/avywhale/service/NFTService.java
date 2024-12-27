package com.example.avywhale.service;

import com.example.avywhale.dto.NFTRequestDTO;
import com.example.avywhale.dto.NFTResponseDTO;
import com.example.avywhale.exception.TokenomicsException;
import com.example.avywhale.model.NFT;
import com.example.avywhale.repository.NFTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NFTService {

    private final NFTRepository nftRepository;

    @Autowired
    public NFTService(NFTRepository nftRepository) {
        this.nftRepository = nftRepository;
    }


    public NFTResponseDTO createNFT(NFTRequestDTO nftRequestDTO) {
        NFT nft = new NFT();
        nft.setName(nftRequestDTO.getName());
        nft.setMetadataUri(nftRequestDTO.getMetadataUri());
        nft.setCreatorAddress(nftRequestDTO.getCreatorAddress());

        NFT savedNFT = nftRepository.save(nft);

        return new NFTResponseDTO(
                savedNFT.getId(),
                savedNFT.getName(),
                savedNFT.getMetadataUri(),
                savedNFT.getCreatorAddress()
        );
    }


    public List<NFTResponseDTO> getAllNFTs() {
        return nftRepository.findAll()
                .stream()
                .map(nft -> new NFTResponseDTO(
                        nft.getId(),
                        nft.getName(),
                        nft.getMetadataUri(),
                        nft.getCreatorAddress()
                ))
                .toList();
    }


    public NFTResponseDTO getNFTById(Long nftId) {
        Optional<NFT> optionalNFT = nftRepository.findById(nftId);
        if (optionalNFT.isEmpty()) {
            throw new TokenomicsException("NFT with ID " + nftId + " not found.");
        }

        NFT nft = optionalNFT.get();
        return new NFTResponseDTO(
                nft.getId(),
                nft.getName(),
                nft.getMetadataUri(),
                nft.getCreatorAddress()
        );
    }


    public void deleteNFT(Long nftId) {
        if (!nftRepository.existsById(nftId)) {
            throw new TokenomicsException("NFT with ID " + nftId + " does not exist.");
        }
        nftRepository.deleteById(nftId);
    }
}
