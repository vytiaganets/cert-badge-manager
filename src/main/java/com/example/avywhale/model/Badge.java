package com.example.avywhale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "badges")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "badge_name")
    private String name;
    private String description;
    private String awardedTo;
    @Column(name = "issued_date")
    private LocalDate awardedDate;
    @Column(name = "issuer", nullable = false)
    private String issuer;
    private String blockchainTransactionId;

    public Badge(String name, String description, LocalDate awardedDate) {
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
