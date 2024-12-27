package com.example.avywhale.controller;

import com.example.avywhale.dto.NFTRequestDTO;
import com.example.avywhale.dto.NFTResponseDTO;
import com.example.avywhale.service.NFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nft")
public class NFTController {
    private final NFTService nftService;

    @Autowired
    public NFTController(NFTService nftService) {
        this.nftService = nftService;
    }

    @PostMapping("/mint")
    public ResponseEntity<NFTResponseDTO> mintNFT(@RequestBody @Validated NFTRequestDTO nftRequestDTO) {
                NFTResponseDTO response = nftService.createNFT(nftRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{tokenId}")
    public ResponseEntity<NFTResponseDTO> getNFTDetails(@PathVariable Long tokenId) {
                NFTResponseDTO response = nftService.getNFTById(tokenId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{tokenId}")
    public ResponseEntity<String> burnNFT(@PathVariable Long tokenId) {
                nftService.deleteNFT(tokenId);
        return ResponseEntity.ok("NFT with tokenId " + tokenId + " has been burned successfully.");
    }
    @GetMapping("/all")
    public ResponseEntity<List<NFTResponseDTO>> getAllNFTs() {
        List<NFTResponseDTO> nfts = nftService.getAllNFTs();
        return ResponseEntity.ok(nfts);
    }


}
