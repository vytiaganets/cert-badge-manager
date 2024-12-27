package com.example.avywhale.util;

import com.example.avywhale.dto.RewardRequestDTO;
import com.example.avywhale.dto.RewardResponseDTO;
import com.example.avywhale.model.Reward;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RewardDistributionUtil {


    public static List<RewardResponseDTO> distributeRewards(RewardRequestDTO rewardRequest, List<Reward> availableRewards) {
        return List.of(createRewardResponse(rewardRequest.getPlayerId(), rewardRequest.getRewardType(), availableRewards));
    }

    public static Reward validateReward(String rewardId, List<Reward> availableRewards) {
        return availableRewards.stream()
                .filter(reward -> reward.getRewardId().toString().equals(rewardId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid reward ID: " + rewardId));
    }


    private static RewardResponseDTO createRewardResponse(String playerId, String rewardId, List<Reward> availableRewards) {
        Reward reward = validateReward(rewardId, availableRewards);
        Map<String, Object> pointsMap = new HashMap<>();
        pointsMap.put("value", reward.getPoints()); 
        return new RewardResponseDTO(
                playerId,
                reward.getName(),
                reward.getDescription(),
                pointsMap,
                reward.isRedeemable()
        );
    }
}
