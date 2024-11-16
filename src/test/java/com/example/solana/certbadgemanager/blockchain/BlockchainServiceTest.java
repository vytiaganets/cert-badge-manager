package com.example.solana.certbadgemanager.blockchain;

import com.example.solana.certbadgemanager.dto.BadgeResponseDTO;
import com.example.solana.certbadgemanager.dto.CertificateRequestDTO;
import com.example.solana.certbadgemanager.dto.BadgeRequestDTO;
import com.example.solana.certbadgemanager.exception.BlockchainException;
import com.example.solana.certbadgemanager.model.Badge;
import com.example.solana.certbadgemanager.model.Certificate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlockchainServiceTest {

    @Mock
    private SolanaClient solanaClient;

    @InjectMocks
    private BlockchainService blockchainService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void writeToBlockchain_ShouldStoreBadgeData() {

        BadgeRequestDTO badgeRequestDTO = new BadgeRequestDTO("User123", "Completion Badge", "2024-10-25");

        Badge badge = convertToBadge(badgeRequestDTO);
        String transactionId = blockchainService.writeBadgeToBlockchain(badge);
        assertNotNull(transactionId);
        assertEquals("mockTransactionId", transactionId);
        verify(blockchainService).writeBadgeToBlockchain(badge);

        when(solanaClient.writeBadgeData(any(BadgeRequestDTO.class)))
                .thenReturn("mockTransactionId");

    }

    public Badge convertToBadge(BadgeRequestDTO badgeRequestDTO) {
        return new Badge(
                badgeRequestDTO.getName(),
                badgeRequestDTO.getDescription(),
                badgeRequestDTO.getIssueDate()
        );
    }

    @Test
    void writeToBlockchain_ShouldStoreCertificateData() {
        CertificateRequestDTO certificateRequestDTO = new CertificateRequestDTO("User123", "Java Basics", "2024-10-25");

        when(solanaClient.writeToBlockchain(any(CertificateRequestDTO.class)))
                .thenReturn("mockTransactionId");

        String transactionId = blockchainService.writeCertificateToBlockchain(certificateRequestDTO);

        assertNotNull(transactionId);
        assertEquals("mockTransactionId", transactionId);
        verify(solanaClient, times(1)).writeCertificateData(certificateRequestDTO);
    }

    public Certificate convertToCertificate(CertificateRequestDTO certificateRequestDTO) {
        return new Certificate(
                certificateRequestDTO.getTitle(),
                certificateRequestDTO.getDescription(),
                certificateRequestDTO.getIssueDate()
        );
    }

    @Test
    void readFromBlockchain_ShouldReturnBadgeData() {
        String badgeId = "badge123";
        BadgeResponseDTO mockBadge = new BadgeResponseDTO("User123", "Completion Badge", "2024-10-25");

        when(solanaClient.readBadgeData(badgeId)).thenReturn(String.valueOf(mockBadge));

        Badge badgeData = blockchainService.readBadgeFromBlockchain(badgeId);

        assertNotNull(badgeData);
        assertEquals("Completion Badge", badgeData.getDescription());
        assertEquals("2024-10-25", badgeData.getAwardedDate());
        verify(solanaClient, times(1)).readBadgeData(badgeId);
    }

    @Test
    void readFromBlockchain_ShouldThrowExceptionForInvalidId() {
        String invalidId = "invalidId";

        when(solanaClient.readBadgeData(invalidId))
                .thenThrow(new BlockchainException("Data not found on blockchain"));

        Exception exception = assertThrows(BlockchainException.class, () ->
                blockchainService.readCertificateFromBlockchain(invalidId));

        assertEquals("Data not found on blockchain", exception.getMessage());
        verify(solanaClient, times(1)).readBadgeData(invalidId);
    }


}
