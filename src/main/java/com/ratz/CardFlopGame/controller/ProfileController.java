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
import org.springframework.web.bind.annotation.PathVariable;
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

        return buildProfileResponse(player, profile);
    }


    @GetMapping("/{username}")
    public ResponseEntity<ProfileResponseDTO> getProfileByUsername(@PathVariable String username) {
        Player player = playerService.getPlayerByUsername(username);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }

        Profile profile = profileService.getProfileByPlayerId(player.getId());
        return buildProfileResponse(player, profile);
    }


    private ResponseEntity<ProfileResponseDTO> buildProfileResponse(Player player, Profile profile) {
        ProfileResponseDTO profileResponseDTO;

        if (profile == null) {
            log.error("Profile not found for player with id: " + player.getId() + "setting up a new and empty profile");
            profileResponseDTO = new ProfileResponseDTO();
        } else {
            profileResponseDTO = ProfileMapper.INSTANCE.profileToProfileDTO(profile);
        }

        profileResponseDTO.setUsername(player.getUsername());
        profileResponseDTO.setCreatedAt(player.getCreatedAt());
        profileResponseDTO.setFriends(player.getFriends()); // TODO change para DTO

        return ResponseEntity.ok(profileResponseDTO);
    }

    private URI getURI() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/player/get/<playerId>").toUriString());
    }
}
