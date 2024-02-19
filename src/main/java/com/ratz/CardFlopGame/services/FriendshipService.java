package com.ratz.CardFlopGame.services;

public interface FriendshipService {

    void sendFriendRequest(Long senderId, Long targetUserId);

    void acceptFriendRequest(Long friendshipId, Long playerId);

    void removeFriend(Long friendshipId, Long playerId);
}
