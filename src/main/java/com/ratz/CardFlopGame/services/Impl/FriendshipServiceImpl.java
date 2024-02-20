package com.ratz.CardFlopGame.services.Impl;

import com.ratz.CardFlopGame.DTO.FriendshipDTO;
import com.ratz.CardFlopGame.entity.Friendship;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.exception.ApiException;
import com.ratz.CardFlopGame.repository.FriendshipRepository;
import com.ratz.CardFlopGame.repository.PlayerRepository;
import com.ratz.CardFlopGame.services.FriendshipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final PlayerRepository playerRepository;


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

    public void blockFriend(Long friendshipId, Long playerId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new ApiException("Friendship not found."));

        if (!friendship.getPlayer().getId().equals(playerId) && !friendship.getFriend().getId().equals(playerId)) {
            throw new ApiException("Not authorized to block this friend.");
        }

        friendship.setBlocked(true);
        friendshipRepository.save(friendship);
        log.info("User {} blocked friendship {}", playerId, friendshipId);
    }

    public void unblockFriend(Long friendshipId, Long playerId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new ApiException("Friendship not found."));

        if (!friendship.getPlayer().getId().equals(playerId) && !friendship.getFriend().getId().equals(playerId)) {
            throw new ApiException("Not authorized to unblock this friend.");
        }

        friendship.setBlocked(false);
        friendshipRepository.save(friendship);
        log.info("User {} unblocked friendship {}", playerId, friendshipId);
    }

    public Page<FriendshipDTO> listFriends(Long userId, int page, int size) {
        log.info("Listing friends for user {}", userId);

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Friendship> friendships = friendshipRepository.findByPlayerIdOrFriendIdAndAcceptedIsTrue(userId, userId, pageable);

        return friendships.map(friendship -> {
            FriendshipDTO friendshipDTO = new FriendshipDTO();
            friendshipDTO.setFriendId(friendship.getFriend().getId());
            friendshipDTO.setFriendUsername(friendship.getFriend().getUsername());
            friendshipDTO.setBlocked(friendship.isBlocked());
            friendshipDTO.setFriendshipDate(friendship.getFriendshipDate());
            return friendshipDTO;
        });
    }

    public Page<FriendshipDTO> listAllFriendships(Long userId, int page, int size) {
        log.info("Listing all friendships for user {}", userId);
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<Friendship> friendships = friendshipRepository.findByPlayerIdOrFriendId(userId, userId, pageable);

        return friendships.map(this::convertToFriendshipDTO);
    }

    public Page<FriendshipDTO> listSentFriendRequests(Long userId, int page, int size) {
        log.info("Listing sent friendship requests for user {}", userId);
        Pageable pageable = PageRequest.of(page, size);
        Page<Friendship> friendships = friendshipRepository.findByPlayerIdAndAcceptedIsFalse(userId, pageable);
        return friendships.map(this::convertToFriendshipDTO);
    }

    public Page<FriendshipDTO> listReceivedFriendRequests(Long userId, int page, int size) {
        log.info("Listing received friendship requests for user {}", userId);
        Pageable pageable = PageRequest.of(page, size);
        Page<Friendship> friendships = friendshipRepository.findByFriendIdAndAcceptedIsFalse(userId, pageable);
        return friendships.map(this::convertToFriendshipDTO);
    }

    private FriendshipDTO convertToFriendshipDTO(Friendship friendship) {
        FriendshipDTO friendshipDTO = new FriendshipDTO();

        friendshipDTO.setFriendId(friendship.getFriend().getId());
        friendshipDTO.setFriendUsername(friendship.getFriend().getUsername());
        friendshipDTO.setAccepted(friendship.isAccepted());
        friendshipDTO.setBlocked(friendship.isBlocked());
        friendshipDTO.setFriendshipDate(friendship.getFriendshipDate());
        return friendshipDTO;
    }
}
