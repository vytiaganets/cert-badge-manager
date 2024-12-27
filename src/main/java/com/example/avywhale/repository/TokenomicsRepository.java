package com.example.avywhale.repository;

import com.example.avywhale.model.Tokenomics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenomicsRepository extends JpaRepository<Tokenomics, Long> {

}
