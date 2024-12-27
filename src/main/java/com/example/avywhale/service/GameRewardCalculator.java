package com.example.avywhale.service;

import com.example.avywhale.dto.GameAchievementResponseDTO;
import com.example.avywhale.exception.TokenomicsException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameRewardCalculator {

    private static final int POINTS_PER_REWARD_UNIT = 100;


    public int calculateReward(List<GameAchievementResponseDTO> achievements) {
        if (achievements == null || achievements.isEmpty()) {
            throw new TokenomicsException("Achievements list is empty or null.");
        }

        int totalPoints = achievements.stream()
                .mapToInt(GameAchievementResponseDTO::getPoints)
                .sum();

        return totalPoints / POINTS_PER_REWARD_UNIT;
    }


    public int calculateBonusReward(List<GameAchievementResponseDTO> achievements) {
        if (achievements == null || achievements.isEmpty()) {
            throw new TokenomicsException("Achievements list is empty or null.");
        }

                int totalPoints = achievements.stream()
                .mapToInt(GameAchievementResponseDTO::getPoints)
                .sum();

        return totalPoints / 500;
    }


    public int calculateTotalReward(List<GameAchievementResponseDTO> achievements) {
        int baseReward = calculateReward(achievements);
        int bonusReward = calculateBonusReward(achievements);
        return baseReward + bonusReward;
    }
}
