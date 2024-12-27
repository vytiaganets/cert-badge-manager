package com.example.avywhale.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player_progress")
public class PlayerProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_id", nullable = false)
    private String playerId;

    @Column(name = "game_id", nullable = false)
    private String gameId;

    @Column(name = "current_level", nullable = false)
    private int currentLevel;

    @Column(name = "experience_points", nullable = false)
    private int experiencePoints;

    @Column(name = "last_updated", nullable = false)
    private LocalDateTime lastUpdated;

        @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "player_progress_id")
    private List<GameAchievement> achievements = new ArrayList<>();

        @Column(name = "total_points", nullable = false)
    private int totalPoints;

    public PlayerProgress(String playerId) {
    }

        public void addAchievement(GameAchievement gameAchievement) {
        achievements.add(gameAchievement);
        totalPoints += gameAchievement.getPoints();
    }

        @Override
    public String toString() {
        return "PlayerProgress{" +
                "id=" + id +
                ", playerId='" + playerId + '\'' +
                ", gameId='" + gameId + '\'' +
                ", currentLevel=" + currentLevel +
                ", experiencePoints=" + experiencePoints +
                ", lastUpdated=" + lastUpdated +
                ", achievements=" + achievements +
                ", totalPoints=" + totalPoints +
                '}';
    }
}
