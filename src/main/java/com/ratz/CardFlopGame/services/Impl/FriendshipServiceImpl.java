package com.ratz.CardFlopGame.services.Impl;

import com.ratz.CardFlopGame.entity.Friendship;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.exception.ApiException;
import com.ratz.CardFlopGame.repository.FriendshipRepository;
import com.ratz.CardFlopGame.repository.PlayerRepository;
import com.ratz.CardFlopGame.services.FriendshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final PlayerRepository playerRepository;

    @Override
    public void sendFriendRequest(Long senderId, Long targetUserId) {

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
}
