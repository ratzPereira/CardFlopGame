package com.ratz.CardFlopGame.repository;

import com.ratz.CardFlopGame.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    boolean existsByPlayerIdAndFriendId(Long playerId, Long friendId);
}
