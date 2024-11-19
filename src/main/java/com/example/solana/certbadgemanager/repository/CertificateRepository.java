package com.example.solana.certbadgemanager.repository;

import com.example.solana.certbadgemanager.dto.CertificateResponseDTO;
import com.example.solana.certbadgemanager.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, String>{
//    void delete(Certificate certificate);

    @Modifying
    @Query("DELETE FROM Certificate c WHERE c.id = :id")
    void deleteById(@Param("id") Long id);
}
