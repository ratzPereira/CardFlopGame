package com.ratz.CardFlopGame.controller;

import com.ratz.CardFlopGame.DTO.ProfileResponseDTO;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.entity.Profile;
import com.ratz.CardFlopGame.mapper.PlayerMapper;
import com.ratz.CardFlopGame.mapper.ProfileMapper;
import com.ratz.CardFlopGame.services.PlayerService;
import com.ratz.CardFlopGame.services.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/player/profile")
@Slf4j
public class ProfileController {
    private final PlayerMapper playerMapper;

    private final ProfileService profileService;
    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<ProfileResponseDTO> getCurrentPlayerProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Player player = playerService.getPlayerByEmail(currentUsername);
        Profile profile = profileService.getProfileByPlayerId(player.getId());

        if (profile == null) {

            log.error("Profile not found for player with id: " + player.getId());

            ProfileResponseDTO emptyProfileDTO = new ProfileResponseDTO();
            emptyProfileDTO.setUsername(player.getUsername());
            emptyProfileDTO.setCreatedAt(player.getCreatedAt());
            emptyProfileDTO.setFriends(player.getFriends());

            return ResponseEntity.ok(emptyProfileDTO);
        }

        ProfileResponseDTO profileResponseDTO = ProfileMapper.INSTANCE.profileToProfileDTO(profile);

        profileResponseDTO.setFriends(player.getFriends());
        profileResponseDTO.setUsername(player.getUsername());
        profileResponseDTO.setCreatedAt(player.getCreatedAt());

        return ResponseEntity.ok(profileResponseDTO);
    }

    private URI getURI() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/player/get/<playerId>").toUriString());
    }
}
