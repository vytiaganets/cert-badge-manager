package com.example.avywhale.repository;

import com.example.avywhale.model.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {


    Optional<Reward> findByRewardId(Long rewardId);


    List<Reward> findByPlayerId(String playerId);


    List<Reward> findByRedeemed(boolean redeemed);


    List<Reward> findByRewardType(String rewardType);
}
