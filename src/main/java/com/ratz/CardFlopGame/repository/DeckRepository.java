package com.ratz.CardFlopGame.repository;

import com.ratz.CardFlopGame.entity.Deck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeckRepository extends JpaRepository<Deck, Long> {
}
