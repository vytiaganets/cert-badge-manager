package com.example.solana.certbadgemanager.blockchain;

import com.example.solana.certbadgemanager.model.Badge;
import com.example.solana.certbadgemanager.model.Certificate;
import org.springframework.stereotype.Service;

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

    public Badge readBadgeFromBlockchain(String badgeId){
        Badge badge = new Badge();
        badge.setId(badgeId);
        badge.setName("Mock Badge Name");
        return badge;
    }

    public Certificate readCertificateFromBlockchain(String certificateId){
        Certificate certificate = new Certificate();
        certificate.setId(certificateId);
        certificate.setTitle("Mock Certificate Name");
        return certificate;
    }
}
