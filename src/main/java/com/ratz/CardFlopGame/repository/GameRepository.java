package com.ratz.CardFlopGame.repository;

import com.ratz.CardFlopGame.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
