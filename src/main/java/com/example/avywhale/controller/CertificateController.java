package com.example.avywhale.controller;

import com.example.avywhale.dto.CertificateRequestDTO;
import com.example.avywhale.dto.CertificateResponseDTO;
import com.example.avywhale.exception.CertificateNotFoundException;
import com.example.avywhale.model.Certificate;
import com.example.avywhale.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping("/create")
    public CertificateResponseDTO createCertificate(@RequestBody CertificateRequestDTO certificateRequestDTO) {
        Certificate certificate = certificateService.createCertificate(certificateRequestDTO);
        return mapToResponseDTO(certificate);
    }

    private CertificateResponseDTO mapToResponseDTO(Certificate certificate) {
        return new CertificateResponseDTO(
                certificate.getId().toString(),
                certificate.getTitle(),
                certificate.getDescription()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<CertificateResponseDTO> getCertificateById(@PathVariable String id) {
        try {
            CertificateResponseDTO certificateResponse = certificateService.getCertificateById(id);
            return ResponseEntity.ok(certificateResponse);
        } catch (CertificateNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public List<Certificate> getAllCertificates() {
        return certificateService.getAllCertificates();
    }

    @DeleteMapping("/{id}")
    public void deleteCertificate(@PathVariable UUID id) {
        certificateService.deleteCertificateById(id);
    }
}
