package com.example.solana.certbadgemanager.repository;

import com.example.solana.certbadgemanager.model.Tokenomics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenomicsRepository extends JpaRepository<Tokenomics, Long> {

}
