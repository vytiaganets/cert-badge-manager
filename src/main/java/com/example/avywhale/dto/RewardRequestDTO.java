package com.example.avywhale.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RewardRequestDTO {

    @NotBlank(message = "Reward type must not be blank")
    private String rewardType;

    @NotNull(message = "Reward details cannot be null")
    private Map<String, Object> rewardDetails;

    @NotBlank(message = "Player ID must not be blank")
    private String playerId;

    @Positive(message = "Reward amount must be positive")
    private int rewardAmount;

    @NotNull(message = "Achievements cannot be null")
    private List<String> achievements;
}
