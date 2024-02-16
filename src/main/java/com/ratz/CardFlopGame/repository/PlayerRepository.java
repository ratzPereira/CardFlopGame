package com.ratz.CardFlopGame.repository;

import com.ratz.CardFlopGame.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Player findByEmail(String email);

    Optional<Player> findByUsername(String username);
}
