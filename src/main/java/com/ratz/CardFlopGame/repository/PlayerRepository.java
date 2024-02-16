package com.ratz.CardFlopGame.repository;

import com.ratz.CardFlopGame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
