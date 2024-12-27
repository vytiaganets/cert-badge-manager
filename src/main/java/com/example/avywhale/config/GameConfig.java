package com.example.avywhale.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {

    @Value("${game.maxPlayers}")
    private int maxPlayers;

    @Value("${game.startingCoins}")
    private int startingCoins;

    @Value("${game.mapSize}")
    private String mapSize;

    @Value("${game.difficultyLevel}")
    private String difficultyLevel;

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getStartingCoins() {
        return startingCoins;
    }

    public String getMapSize() {
        return mapSize;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    @Override
    public String toString() {
        return "GameConfig{" +
                "maxPlayers=" + maxPlayers +
                ", startingCoins=" + startingCoins +
                ", mapSize='" + mapSize + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                '}';
    }
}
