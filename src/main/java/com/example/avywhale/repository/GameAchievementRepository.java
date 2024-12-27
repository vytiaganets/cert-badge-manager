package com.example.avywhale.repository;

import com.example.avywhale.model.GameAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameAchievementRepository extends JpaRepository<GameAchievement, Long> {
    List<GameAchievement> findByPlayerId(String playerId);
}
