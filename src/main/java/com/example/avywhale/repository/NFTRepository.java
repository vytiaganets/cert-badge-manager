package com.example.avywhale.repository;

import com.example.avywhale.model.NFT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface NFTRepository extends JpaRepository<NFT, Long> {


    Optional<NFT> findByTokenId(String tokenId);


    List<NFT> findByOwnerWallet(String walletAddress);


    List<NFT> findByMetadataUri(String metadataUri);
}
