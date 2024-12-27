package com.example.avywhale.service;

import com.example.avywhale.dto.GameAchievementRequestDTO;
import com.example.avywhale.dto.GameAchievementResponseDTO;
import com.example.avywhale.dto.GameActionRequestDTO;
import com.example.avywhale.dto.GameActionResponseDTO;
import com.example.avywhale.exception.TokenomicsException;
import com.example.avywhale.model.GameAchievement;
import com.example.avywhale.repository.GameAchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GameMechanicsService {

    private final GameAchievementRepository gameAchievementRepository;

    @Autowired
    public GameMechanicsService(GameAchievementRepository gameAchievementRepository) {
        this.gameAchievementRepository = gameAchievementRepository;
    }


    public List<GameAchievementResponseDTO> getAchievementsForPlayer(String playerId) {
        List<GameAchievement> achievements = gameAchievementRepository.findByPlayerId(playerId);

        if (achievements.isEmpty()) {
            throw new TokenomicsException("No achievements found for player ID: " + playerId);
        }

        return achievements.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }


    public GameAchievementResponseDTO addAchievement(GameAchievementRequestDTO achievementRequest) {
        GameAchievement achievement = new GameAchievement();
        achievement.setPlayerId(achievementRequest.getPlayerId());
        achievement.setName(achievementRequest.getName());
        achievement.setDescription(achievementRequest.getDescription());
        achievement.setPoints(achievementRequest.getPoints());

        GameAchievement savedAchievement = gameAchievementRepository.save(achievement);

        return mapToResponseDTO(savedAchievement);
    }


    public GameAchievementResponseDTO updateAchievement(Long achievementId,
                                                        GameAchievementRequestDTO achievementRequest) {
        GameAchievement achievement = gameAchievementRepository.findById(achievementId)
                .orElseThrow(() -> new TokenomicsException("Achievement not found for ID: " + achievementId));

        achievement.setName(achievementRequest.getName());
        achievement.setDescription(achievementRequest.getDescription());
        achievement.setPoints(achievementRequest.getPoints());

        GameAchievement updatedAchievement = gameAchievementRepository.save(achievement);

        return mapToResponseDTO(updatedAchievement);
    }


    public void deleteAchievement(Long achievementId) {
        if (!gameAchievementRepository.existsById(achievementId)) {
            throw new TokenomicsException("Achievement not found for ID: " + achievementId);
        }

        gameAchievementRepository.deleteById(achievementId);
    }


    private GameAchievementResponseDTO mapToResponseDTO(GameAchievement achievement) {
        return new GameAchievementResponseDTO(
                achievement.getPlayerId() != null ? Long.valueOf(achievement.getPlayerId()) : null,                 achievement.getAchievementCode(),                 achievement.getName(),                 achievement.getPoints(),                 achievement.isSecret() ? "Locked" : "Unlocked"         );
    }

        public GameActionResponseDTO performGameAction(GameActionRequestDTO requestDTO) {
                if (requestDTO == null || requestDTO.getPlayerId() == null) {
            throw new TokenomicsException("Invalid action request");
        }

                String actionResult = "Action '" + requestDTO.getAction() + "' performed successfully for player " + requestDTO.getPlayerId();

                return new GameActionResponseDTO("success", actionResult, null);
    }

        public String getPlayerStatus(String playerId) {
        if (playerId == null || playerId.isEmpty()) {
            throw new TokenomicsException("Player ID cannot be null or empty");
        }

                return "Player " + playerId + " is currently active with level 10.";
    }

        public void updatePlayerLevel(String playerId, int newLevel) {
        if (playerId == null || playerId.isEmpty()) {
            throw new TokenomicsException("Player ID cannot be null or empty");
        }

        if (newLevel <= 0) {
            throw new TokenomicsException("Level must be greater than zero");
        }

                System.out.println("Player " + playerId + " level updated to " + newLevel);
    }
}
