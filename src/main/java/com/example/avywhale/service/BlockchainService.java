package com.example.avywhale.service;

import com.example.avywhale.model.Badge;
import com.example.avywhale.model.Certificate;
import org.p2p.solanaj.rpc.RpcClient;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class BlockchainService {

    public String connectToBlockchain() {
        return null;
    }

    public String writeBadgeToBlockchain(Badge badge) {
        if (badge == null || badge.getId() == null) {
            throw new IllegalArgumentException("Badge or Badge ID cannot be null.");
        }

        String transactionId = "mockTransactionId";


        return transactionId;
    }

    public String writeCertificateToBlockchain(Certificate certificate) {
        if (certificate == null || certificate.getId() == null) {
            throw new IllegalArgumentException("Certificate or Certificate ID cannot be null.");
        }

        String transactionId = "mockTransactionId";

        return transactionId;
    }

    public Badge readBadgeFromBlockchain(Long badgeId) {
        if (badgeId == null) {
            throw new IllegalArgumentException("Badge ID cannot be null.");
        }

        Badge badge = new Badge();
        badge.setId(badgeId);
        badge.setName("Mock Badge Name");


        return badge;
    }

    public Certificate readCertificateFromBlockchain(String certificateId) {
        if (certificateId == null || certificateId.isEmpty()) {
            throw new IllegalArgumentException("Certificate ID cannot be null or empty.");
        }

        UUID uuid;
        try {
            uuid = UUID.fromString(certificateId);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid UUID format for certificateId: " + certificateId, e);
        }

        Certificate certificate = new Certificate();
        certificate.setId(uuid);
        certificate.setTitle("Mock Certificate Name");

        return certificate;
    }
}
