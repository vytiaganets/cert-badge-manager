package com.example.solana.certbadgemanager.repository;

import com.example.solana.certbadgemanager.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, String> {
}
