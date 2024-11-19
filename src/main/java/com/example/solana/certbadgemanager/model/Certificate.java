package com.example.solana.certbadgemanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Certificate {
    @Id
    private String id;
    private String title;
    private String description;
    private String awardedTo;
    private LocalDateTime awardedDate;
    private String blockchainTransactionId;
    public Certificate(String title, String description, String awardedTo) {
        this.title = title;
        this.description = description;
        this.awardedTo = awardedTo;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
