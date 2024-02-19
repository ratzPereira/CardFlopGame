package com.ratz.CardFlopGame.services.Impl;

import com.ratz.CardFlopGame.entity.Friendship;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.exception.ApiException;
import com.ratz.CardFlopGame.repository.FriendshipRepository;
import com.ratz.CardFlopGame.repository.PlayerRepository;
import com.ratz.CardFlopGame.services.FriendshipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final PlayerRepository playerRepository;

    @Override
    public void sendFriendRequest(Long senderId, Long targetUserId) {

        log.info("Sending friend request from user {} to user {}", senderId, targetUserId);

        if (senderId.equals(targetUserId)) {
            throw new ApiException("Impossible to send a friend request to yourself.");
        }

        boolean exists = friendshipRepository.existsByPlayerIdAndFriendId(senderId, targetUserId)
                || friendshipRepository.existsByPlayerIdAndFriendId(targetUserId, senderId);

        if (exists) {
            throw new ApiException("Already friends or request already sent.");
        }

        Player sender = playerRepository.findById(senderId)
                .orElseThrow(() -> new ApiException("User not found."));
        Player friend = playerRepository.findById(targetUserId)
                .orElseThrow(() -> new ApiException("Target user not found."));

        Friendship friendship = new Friendship();
        friendship.setPlayer(sender);
        friendship.setFriend(friend);
        friendship.setAccepted(false);
        friendship.setBlocked(false);

        friendshipRepository.save(friendship);
    }

    public void acceptFriendRequest(Long friendshipId, Long playerId) {

        log.info("Accepting friend request {} by user {}", friendshipId, playerId);

        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new ApiException("Friendship not found."));

        if (!friendship.getFriend().getId().equals(playerId)) {
            throw new ApiException("You can only accept friend requests sent to you.");
        }

        friendship.setAccepted(true);
        friendshipRepository.save(friendship);
    }

    public void removeFriend(Long friendshipId, Long playerId) {

        log.info("Removing friendship {} by user {}", friendshipId, playerId);
        
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new ApiException("Friendship not found."));

        if (!friendship.getPlayer().getId().equals(playerId) && !friendship.getFriend().getId().equals(playerId)) {
            throw new ApiException("You can only remove friends that are yours.");
        }

        friendshipRepository.delete(friendship);
    }
}
