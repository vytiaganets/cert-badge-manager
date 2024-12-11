package com.example.solana.certbadgemanager.repository;

import com.example.solana.certbadgemanager.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    boolean existsByName(String name);
    Optional<Badge> findById(Long id);
}
