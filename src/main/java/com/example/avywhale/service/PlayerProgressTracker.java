package com.example.avywhale.service;

import com.example.avywhale.model.GameAchievement;
import com.example.avywhale.model.PlayerProgress;
import com.example.avywhale.repository.PlayerProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PlayerProgressTracker {

    private final PlayerProgressRepository playerProgressRepository;

    @Autowired
    public PlayerProgressTracker(PlayerProgressRepository playerProgressRepository) {
        this.playerProgressRepository = playerProgressRepository;
    }


    public void updatePlayerProgress(String playerId, GameAchievement gameAchievement) {
        PlayerProgress progress = playerProgressRepository.findByPlayerId(playerId)
                .orElse(new PlayerProgress(playerId));

        progress.getAchievements().add(gameAchievement);
        progress.setTotalPoints(progress.getTotalPoints() + gameAchievement.getPoints());
        playerProgressRepository.save(progress);
    }


    public PlayerProgress getPlayerProgress(String playerId) {
        return playerProgressRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player progress not found for ID: " + playerId));
    }


    public List<GameAchievement> getPlayerAchievements(String playerId) {
        PlayerProgress progress = getPlayerProgress(playerId);
        return progress.getAchievements();
    }


    public void resetPlayerProgress(String playerId) {
        PlayerProgress progress = getPlayerProgress(playerId);
        progress.getAchievements().clear();
        progress.setTotalPoints(0);
        playerProgressRepository.save(progress);
    }
}
