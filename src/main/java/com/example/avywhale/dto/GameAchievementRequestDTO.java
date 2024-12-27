package com.example.avywhale.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameAchievementRequestDTO {

    @NotBlank(message = "Player ID cannot be blank")
    private String playerId;

    @NotBlank(message = "Achievement name cannot be blank")
    private String name;

    private String description;

    @NotBlank(message = "Achievement ID cannot be blank")
    private String achievementId;

    @NotNull(message = "Points cannot be null")
    private Integer points;

}
