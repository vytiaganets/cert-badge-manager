package com.example.solana.certbadgemanager.service;

import com.example.solana.certbadgemanager.dto.CertificateRequestDTO;
import com.example.solana.certbadgemanager.dto.CertificateResponseDTO;
import com.example.solana.certbadgemanager.mapper.Mappers;
import com.example.solana.certbadgemanager.model.Certificate;
import com.example.solana.certbadgemanager.repository.CertificateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificateService {
    private final CertificateRepository certificateRepository;
    private final Mappers mappers;
    @Autowired
    public CertificateService(CertificateRepository certificateRepository){
        this.certificateRepository = certificateRepository;
    }


    public CertificateResponseDTO getCertificateById(String id){
        Certificate certificate = certificateRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Certificate not found"));
        return new CertificateResponseDTO(certificate.getId(), certificate.getTitle(), certificate.getDescription());
    }
    public CertificateResponseDTO getCertificateResponseDTO(Certificate certificate) {
        return mappers.toCertificateResponseDto(certificate);
    }

    public CertificateResponseDTO createCertificate(CertificateRequestDTO certificateRequestDTO) {
        Certificate certificate = mappers.toCertificateEntity(certificateRequestDTO);

        Certificate savedCertificate = certificateRepository.save(certificate);

        return mappers.toCertificateResponseDTO(savedCertificate);
    }
    public void deleteCertificate(Long id) {
        CertificateResponseDTO certificate = getCertificateById(String.valueOf(id));
        certificateRepository.delete(certificate);
    }
}
