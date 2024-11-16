package com.example.solana.certbadgemanager.service;

import com.example.solana.certbadgemanager.dto.CertificateRequestDTO;
import com.example.solana.certbadgemanager.dto.CertificateResponseDTO;
import com.example.solana.certbadgemanager.model.Certificate;
import com.example.solana.certbadgemanager.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertificateService {
    private final CertificateRepository certificateRepository;

    @Autowired
    public CertificateService(CertificateRepository certificateRepository){
        this.certificateRepository = certificateRepository;
    }

    public CertificateResponseDTO createCertificate(CertificateRequestDTO certificateRequestDTO){
        Certificate certificate = new Certificate();
        certificate.setTitle(certificateRequestDTO.getTitle());
        certificate.setDescription(certificateRequestDTO.getDescription());

        Certificate savedCertificate = certificateRepository.save(certificate);

        return new CertificateResponseDTO(savedCertificate.getId(), savedCertificate.getTitle(),
                savedCertificate.getDescription());
    }

    public CertificateResponseDTO getCertificateById(String id){
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Certificate not found"));
        return new CertificateResponseDTO(certificate.getId(), certificate.getTitle(), certificate.getDescription());
    }

    public void deleteCertificate(Long id) {
        CertificateResponseDTO certificate = getCertificateById(String.valueOf(id));
        certificateRepository.delete(certificate);
    }
}
