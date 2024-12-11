package com.example.solana.certbadgemanager.service;

import com.example.solana.certbadgemanager.dto.CertificateRequestDTO;
import com.example.solana.certbadgemanager.dto.CertificateResponseDTO;
import com.example.solana.certbadgemanager.exception.CertificateNotFoundException;
import com.example.solana.certbadgemanager.mapper.Mappers;
import com.example.solana.certbadgemanager.model.Certificate;
import com.example.solana.certbadgemanager.repository.CertificateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CertificateService {
    private final CertificateRepository certificateRepository;
    @Autowired
    private Mappers mappers;

    @Autowired
    public CertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }
    public CertificateResponseDTO getCertificateById(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new CertificateNotFoundException("Invalid UUID format for ID: " + id);
        }

        Optional<Certificate> certificate = certificateRepository.findById(uuid);
        if (certificate.isPresent()) {
            return mapToResponseDTO(certificate.get());
        } else {
            throw new CertificateNotFoundException("Certificate with ID " + id + " not found");
        }
    }
    public void deleteCertificateById(UUID id) {
        certificateRepository.deleteById(id);
    }
    private CertificateResponseDTO mapToResponseDTO(Certificate certificate) {
        return new CertificateResponseDTO(
                certificate.getId().toString(),
                certificate.getTitle(),
                certificate.getDescription(),
                certificate.getUser() != null ? certificate.getUser().getId() : null,
                certificate.getAwardedTo(),
                certificate.getBlockchainTransactionId(),
                null,
                certificate.getAwardedTo(),
                certificate.getAwardedDate() != null ? certificate.getAwardedDate().toString() : null
        );
    }


    public CertificateResponseDTO getCertificateResponseDTO(Certificate certificate) {
        return mappers.toCertificateResponseDto(certificate);
    }

    public Certificate createCertificate(CertificateRequestDTO requestDTO) {
        Certificate certificate = new Certificate();
        certificate.setTitle(requestDTO.getTitle());
        certificate.setDescription(requestDTO.getDescription());
        certificate.setAwardedDate(LocalDateTime.now());
        return certificateRepository.save(certificate);
    }

}
