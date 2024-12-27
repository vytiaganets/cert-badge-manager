package com.example.avywhale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameAchievementResponseDTO {

    private Long playerId;
    private String achievementId;
    private String achievementName;
    private Integer points;
    private String status;         public GameAchievementResponseDTO(String achievementName) {
        this.achievementName = achievementName;
    }
}
