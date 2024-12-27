package com.example.avywhale.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NFTRequestDTO {

    @NotBlank(message = "Creator private key cannot be blank")
    private String creatorPrivateKey;

    @NotBlank(message = "Metadata URI cannot be blank")
    private String metadataUri;

    @NotBlank(message = "Recipient address cannot be blank")
    private String recipientAddress;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Creator address cannot be blank")
    private String creatorAddress;
}
