package com.example.solana.certbadgemanager.repository;

import com.example.solana.certbadgemanager.dto.CertificateResponseDTO;
import com.example.solana.certbadgemanager.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, String> {
    void delete(CertificateResponseDTO certificate);
}
