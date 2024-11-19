package com.example.solana.certbadgemanager.repository;

import com.example.solana.certbadgemanager.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {

}
