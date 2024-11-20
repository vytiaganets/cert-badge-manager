package com.example.solana.certbadgemanager.model;

import com.example.solana.certbadgemanager.dto.BadgeRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "badges")
public class Badge {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String description;
    private String awardedTo;
    private LocalDateTime awardedDate;
    private String blockchainTransactionId;

    public Badge(String name, String description, LocalDateTime awardedDate) {
        this.name = name;
        this.description = description;
        this.awardedDate = awardedDate;
    }

    public Badge(String name, String description, String awardedTo) {
        this.name = name;
        this.description = description;
        this.awardedTo = awardedTo;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
