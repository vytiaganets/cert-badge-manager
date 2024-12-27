package com.example.avywhale.service;

import com.example.avywhale.dto.GameAchievementResponseDTO;
import com.example.avywhale.dto.RewardRequestDTO;
import com.example.avywhale.dto.RewardResponseDTO;
import com.example.avywhale.exception.TokenomicsException;
import com.example.avywhale.model.Reward;
import com.example.avywhale.repository.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RewardService {

    private final RewardRepository rewardRepository;
    private final GameRewardCalculator gameRewardCalculator;

    @Autowired
    public RewardService(RewardRepository rewardRepository, GameRewardCalculator gameRewardCalculator) {
        this.rewardRepository = rewardRepository;
        this.gameRewardCalculator = gameRewardCalculator;
    }


    public RewardResponseDTO distributeRewards(RewardRequestDTO rewardRequestDTO) {
        if (rewardRequestDTO.getPlayerId() == null || rewardRequestDTO.getAchievements() == null) {
            throw new TokenomicsException("Invalid reward request data.");
        }
                List<GameAchievementResponseDTO> achievements = rewardRequestDTO.getAchievements()
                .stream()
                .map(achievement -> new GameAchievementResponseDTO(achievement))                 .collect(Collectors.toList());

        int totalReward = gameRewardCalculator.calculateTotalReward(achievements);

        Reward reward = new Reward();
        reward.setPlayerId(rewardRequestDTO.getPlayerId());
        reward.setAmount(totalReward);
        reward.setDescription("Reward distributed based on achievements");

        Reward savedReward = rewardRepository.save(reward);

                Map<String, Object> points = Map.of(
                "totalPoints", savedReward.getAmount()         );

        return new RewardResponseDTO(
                savedReward.getRewardId().toString(),                 savedReward.getPlayerId(),
                savedReward.getRewardType(),
                points,
                savedReward.isRedeemable()
        );
    }


    public RewardResponseDTO getPlayerRewards(String playerId) {
        List<Reward> rewards = rewardRepository.findByPlayerId(playerId);

        if (rewards.isEmpty()) {
            throw new TokenomicsException("No rewards found for player: " + playerId);
        }

        int totalRewards = rewards.stream().mapToInt(Reward::getAmount).sum();
        String rewardDetails = rewards.stream()
                .map(Reward::getDescription)
                .collect(Collectors.joining(", "));

        return new RewardResponseDTO(null, playerId, "Custom Type", totalRewards, false);

    }


    public void redeemReward(String playerId, Long rewardId) {
        Optional<Reward> rewardOptional = rewardRepository.findById(rewardId);

        if (rewardOptional.isEmpty()) {
            throw new TokenomicsException("Reward with ID " + rewardId + " not found.");
        }

        Reward reward = rewardOptional.get();

        if (!reward.getPlayerId().equals(playerId)) {
            throw new TokenomicsException("Reward does not belong to player " + playerId);
        }

        rewardRepository.delete(reward);
    }
}
