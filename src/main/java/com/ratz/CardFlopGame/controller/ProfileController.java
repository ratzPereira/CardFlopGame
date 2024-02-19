package com.ratz.CardFlopGame.controller;

import com.ratz.CardFlopGame.DTO.FriendshipDTO;
import com.ratz.CardFlopGame.DTO.ProfileDTO;
import com.ratz.CardFlopGame.DTO.ProfileResponseDTO;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.entity.Profile;
import com.ratz.CardFlopGame.exception.ApiException;
import com.ratz.CardFlopGame.exception.ProfileAlreadyExistsException;
import com.ratz.CardFlopGame.mapper.FriendshipMapper;
import com.ratz.CardFlopGame.mapper.ProfileMapper;
import com.ratz.CardFlopGame.mapper.ProfileResponseMapper;
import com.ratz.CardFlopGame.services.PlayerService;
import com.ratz.CardFlopGame.services.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/player/profile")
@Slf4j
public class ProfileController {

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

    @GetMapping("/player/{playerId}")
    public ResponseEntity<ProfileResponseDTO> getProfileByPlayerId(@PathVariable Long playerId) {

        Player player = playerService.getPlayerById(playerId);
        if (player == null) {
            return ResponseEntity.notFound().build();
        }

        Profile profile = profileService.getProfileByPlayerId(playerId);
        return buildProfileResponse(player, profile);
    }

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Player player = playerService.getPlayerByEmail(currentUsername);

        if (profileService.getProfileByPlayerId(player.getId()) != null) {
            throw new ProfileAlreadyExistsException(player.getId());
        }

        Profile profile = ProfileMapper.INSTANCE.profileDTOToProfile(profileDTO);
        profile.setPlayer(player);

        Profile createdProfile = profileService.createProfile(profileDTO, player);

        return buildProfileResponse(player, createdProfile);
    }

    @PutMapping
    public ResponseEntity<ProfileDTO> updateProfile(@RequestBody ProfileDTO profileDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Player player = playerService.getPlayerByEmail(currentUsername);
        Profile existingProfile = profileService.getProfileByPlayerId(player.getId());

        if (existingProfile == null) {
            throw new ApiException("Profile not found for player with id: " + player.getId() + "please create a profile first");
        }

        Profile updatedProfile = profileService.updateProfile(profileDTO, player);

        ProfileDTO profileResponseDTO = ProfileMapper.INSTANCE.profileToProfileDTO(updatedProfile);
        return ResponseEntity.ok(profileResponseDTO);
    }

    //helper methods
    private ResponseEntity<ProfileResponseDTO> buildProfileResponse(Player player, Profile profile) {
        ProfileResponseDTO profileResponseDTO;

        if (profile == null) {
            log.error("Profile not found for player with id: " + player.getId() + "setting up a new and empty profile");
            profileResponseDTO = new ProfileResponseDTO();
        } else {
            profileResponseDTO = ProfileResponseMapper.INSTANCE.profileToProfileDTO(profile);
        }

        Set<FriendshipDTO> friendshipDTOs = player.getFriends().stream()
                .map(FriendshipMapper.INSTANCE::friendshipToFriendshipDTO)
                .collect(Collectors.toSet());

        profileResponseDTO.setUsername(player.getUsername());
        profileResponseDTO.setCreatedAt(player.getCreatedAt());
        profileResponseDTO.setFriends(friendshipDTOs);


        return ResponseEntity.ok(profileResponseDTO);
    }

}
