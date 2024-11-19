package com.example.solana.certbadgemanager.controller;

import com.example.solana.certbadgemanager.dto.CertificateRequestDTO;
import com.example.solana.certbadgemanager.dto.CertificateResponseDTO;
import com.example.solana.certbadgemanager.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService){
        this.certificateService = certificateService;
    }

    @PostMapping("/create")
    public ResponseEntity<CertificateResponseDTO> createCertificate(@RequestBody CertificateRequestDTO certificateRequestDTO) {
        CertificateResponseDTO certificateResponse = certificateService.createCertificate(certificateRequestDTO);
        return ResponseEntity.ok(certificateResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificateResponseDTO> getCertificateById(@PathVariable String id){
        CertificateResponseDTO certificateResponse = certificateService.getCertificateById(id);
        return ResponseEntity.ok(certificateResponse);
    }
}
