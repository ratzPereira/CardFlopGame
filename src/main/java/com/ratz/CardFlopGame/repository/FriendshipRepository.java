package com.ratz.CardFlopGame.repository;

import com.ratz.CardFlopGame.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {
}
