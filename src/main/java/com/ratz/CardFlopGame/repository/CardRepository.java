package com.ratz.CardFlopGame.repository;

import com.ratz.CardFlopGame.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
