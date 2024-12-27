package com.example.avywhale.controller;

import com.example.avywhale.service.RewardService;
import com.example.avywhale.dto.RewardRequestDTO;
import com.example.avywhale.dto.RewardResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    @Autowired
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @PostMapping("/distribute")
    public ResponseEntity<RewardResponseDTO> distributeRewards(@RequestBody @Validated RewardRequestDTO rewardRequestDTO) {
        RewardResponseDTO response = rewardService.distributeRewards(rewardRequestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<RewardResponseDTO> getPlayerRewards(@PathVariable String playerId) {
        RewardResponseDTO response = rewardService.getPlayerRewards(playerId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/redeem")
    public ResponseEntity<String> redeemReward(@RequestParam String playerId, @RequestParam Long rewardId) {
        rewardService.redeemReward(playerId, rewardId);
        return ResponseEntity.ok("Reward " + rewardId + " redeemed by player " + playerId);
    }
}
