package com.example.avywhale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RewardResponseDTO {

    private String rewardId;
    private String playerId;
    private String rewardType;
    private Map<String, Object> points;
    private boolean isRedeemed;


    public RewardResponseDTO(String rewardId, String playerId, String rewardType, int totalPoints, boolean isRedeemed) {
        this.rewardId = rewardId;
        this.playerId = playerId;
        this.rewardType = rewardType;
        this.points = Map.of("totalPoints", totalPoints);
        this.isRedeemed = isRedeemed;
    }
}
