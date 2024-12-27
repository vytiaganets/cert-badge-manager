package com.example.avywhale.controller;

import com.example.avywhale.service.GameMechanicsService;
import com.example.avywhale.dto.GameActionRequestDTO;
import com.example.avywhale.dto.GameActionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game-mechanics")
public class GameMechanicsController {

    private final GameMechanicsService gameMechanicsService;

    @Autowired
    public GameMechanicsController(GameMechanicsService gameMechanicsService) {
        this.gameMechanicsService = gameMechanicsService;
    }

    @PostMapping("/perform-action")
    public ResponseEntity<GameActionResponseDTO> performAction(@RequestBody @Validated GameActionRequestDTO requestDTO) {
        GameActionResponseDTO response = gameMechanicsService.performGameAction(requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/status/{playerId}")
    public ResponseEntity<String> getPlayerStatus(@PathVariable String playerId) {
        String status = gameMechanicsService.getPlayerStatus(playerId);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/update-level")
    public ResponseEntity<String> updatePlayerLevel(@RequestParam String playerId, @RequestParam int newLevel) {
        gameMechanicsService.updatePlayerLevel(playerId, newLevel);
        return ResponseEntity.ok("Player " + playerId + " level updated to " + newLevel);
    }
}
