package com.ratz.CardFlopGame.controller;

import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.exception.ApiException;
import com.ratz.CardFlopGame.response.HttpResponse;
import com.ratz.CardFlopGame.services.FriendshipService;
import com.ratz.CardFlopGame.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalTime.now;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/friendships")
public class FriendshipController {

    private final FriendshipService friendshipService;
    private final PlayerService playerService;

    @PostMapping("/send-request/{targetUserId}")
    public ResponseEntity<HttpResponse> sendFriendRequest(@PathVariable Long targetUserId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Player sender = playerService.getPlayerByEmail(currentUsername);

        Player targetUser = playerService.getPlayerById(targetUserId);
        if (targetUser == null) {
            throw new ApiException("User with ID: " + targetUserId + " not found", HttpStatus.NOT_FOUND.toString());
        }

        friendshipService.sendFriendRequest(sender.getId(), targetUserId);

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(now().toString())
                .message("Request sent successfully")
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }

    @PutMapping("/accept/{friendshipId}")
    public ResponseEntity<HttpResponse> acceptFriendRequest(@PathVariable Long friendshipId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Player player = playerService.getPlayerByEmail(currentUsername);
        friendshipService.acceptFriendRequest(friendshipId, player.getId());

        return ResponseEntity.ok(HttpResponse.builder()
                .timeStamp(now().toString())
                .message("Friend request accepted successfully")
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build());
    }
}
