package com.example.avywhale.repository;

import com.example.avywhale.model.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
    boolean existsByName(String name);
    Optional<Badge> findById(Long id);
}
