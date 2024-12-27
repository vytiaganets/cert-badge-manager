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
@Table(name = "investments")
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     private Long id;
    private Double amount;
    private String assetType;
    private LocalDate investmentDate;
    @ManyToOne
    @JoinColumn(name = "investor_id", nullable = false)
    private Investor investor;

}
