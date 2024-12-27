package com.example.avywhale.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CertificateResponseDTO {

    private String id;
    private String title;
    private String description;
    private Long userId;
    private String recipient;
    private String blockchainTransactionId;
    private String status;
    private String issuedTo;
    private String issueDate;
    public CertificateResponseDTO(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

}
