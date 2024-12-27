package com.example.avywhale.repository;

import com.example.avywhale.model.PlayerProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerProgressRepository extends JpaRepository<PlayerProgress, Long> {

        Optional<PlayerProgress> findByPlayerId(String playerId);

        List<PlayerProgress> findByGameId(String gameId);

        boolean existsByPlayerIdAndGameId(String playerId, String gameId);
}
