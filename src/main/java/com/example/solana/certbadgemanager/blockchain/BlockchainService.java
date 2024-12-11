package com.example.solana.certbadgemanager.blockchain;

import com.example.solana.certbadgemanager.model.Badge;
import com.example.solana.certbadgemanager.model.Certificate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BlockchainService {
    public String writeBadgeToBlockchain(Badge badge){
        String transactionId = "mockTransactionId";
        return transactionId;
    }

    public String writeCertificateToBlockchain(Certificate certificate){
        String transactionId = "mockTransactionId";
        return transactionId;
    }

    public Badge readBadgeFromBlockchain(Long badgeId){
        Badge badge = new Badge();
        badge.setId(badgeId);
        badge.setName("Mock Badge Name");
        return badge;
    }

    public Certificate readCertificateFromBlockchain(String certificateId) {
        UUID uuid;
        try {
            uuid = UUID.fromString(certificateId);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format for certificateId: " + certificateId);
        }

        Certificate certificate = new Certificate();
        certificate.setId(uuid);
        certificate.setTitle("Mock Certificate Name");
        return certificate;
    }

}
