package com.example.solana.certbadgemanager.repository;

import com.example.solana.certbadgemanager.model.Certificate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CertificateRepositoryTest {

    @Autowired
    private CertificateRepository certificateRepository;

    @Test
    void saveCertificate_ShouldReturnSavedCertificate() {
        Certificate certificate = new Certificate();
        certificate.setTitle("Completion Certificate");

        Certificate savedCertificate = certificateRepository.save(certificate);

        assertNotNull(savedCertificate.getId());
        assertEquals("Completion Certificate", savedCertificate.getTitle());
    }

    @Test
    void findById_ShouldReturnCertificateIfExists() {
        Certificate certificate = new Certificate();
        certificate.setTitle("Completion Certificate");
        Certificate savedCertificate = certificateRepository.save(certificate);

        Optional<Certificate> foundCertificate = certificateRepository.findById(savedCertificate.getId());

        assertTrue(foundCertificate.isPresent());
        assertEquals("Completion Certificate", foundCertificate.get().getTitle());
    }

    @Test
    void deleteById_ShouldRemoveCertificate() {
        Certificate certificate = new Certificate();
        certificate.setTitle("Temporary Certificate");
        Certificate savedCertificate = certificateRepository.save(certificate);

        certificateRepository.deleteById(savedCertificate.getId());

        Optional<Certificate> deletedCertificate = certificateRepository.findById(savedCertificate.getId());
        assertFalse(deletedCertificate.isPresent());
    }
}
