package com.ratz.CardFlopGame.services;

import com.ratz.CardFlopGame.DTO.FriendshipDTO;
import org.springframework.data.domain.Page;

public interface FriendshipService {

    void sendFriendRequest(Long senderId, Long targetUserId);

    void acceptFriendRequest(Long friendshipId, Long playerId);

    void removeFriend(Long friendshipId, Long playerId);

    void unblockFriend(Long friendshipId, Long playerId);

    void blockFriend(Long friendshipId, Long playerId);

    Page<FriendshipDTO> listFriends(Long userId, int page, int size);
}
