package com.ratz.CardFlopGame.repository;

import com.ratz.CardFlopGame.entity.Friendship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    boolean existsByPlayerIdAndFriendId(Long playerId, Long friendId);

    Page<Friendship> findByPlayerIdOrFriendIdAndAcceptedIsTrue(Long playerId, Long friendId, Pageable pageable);

    Page<Friendship> findByPlayerIdOrFriendId(Long playerId, Long friendId, Pageable pageable);


}
