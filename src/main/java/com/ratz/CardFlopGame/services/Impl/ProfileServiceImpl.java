package com.ratz.CardFlopGame.services.Impl;

import com.ratz.CardFlopGame.DTO.ProfileDTO;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.entity.Profile;
import com.ratz.CardFlopGame.exception.ApiException;
import com.ratz.CardFlopGame.exception.ProfileAlreadyExistsException;
import com.ratz.CardFlopGame.mapper.ProfileMapper;
import com.ratz.CardFlopGame.repository.PlayerRepository;
import com.ratz.CardFlopGame.repository.ProfileRepository;
import com.ratz.CardFlopGame.services.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final PlayerRepository playerRepository;

    @Override
    public Profile getProfileByPlayerId(Long id) {
        log.debug("Fetching profile for player ID: {}", id);
        return profileRepository.getProfileByPlayerId(id);
    }

    @Override
    public Profile createProfile(ProfileDTO profileDTO, Player player) {
        log.info("Creating profile for player ID: {}", player.getId());

        if (player.getId() == null || !playerRepository.existsById(player.getId())) {
            throw new ApiException("Player not found with id: " + player.getId());
        }

        Profile existingProfile = profileRepository.getProfileByPlayerId(player.getId());

        if (existingProfile != null) {
            throw new ProfileAlreadyExistsException(player.getId());
        }

        Profile profile = ProfileMapper.INSTANCE.profileDTOToProfile(profileDTO);
        profile.setPlayer(player);
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(ProfileDTO profileDTO, Player player) {

        log.info("Updating profile for player ID: {}", player.getId());
        Profile profile = profileRepository.getProfileByPlayerId(player.getId());

        if (profile == null) {
            throw new ApiException("Profile not found for player with id: " + player.getId());
        }

        profile.setFirstName(profileDTO.getFirstName());
        profile.setLastName(profileDTO.getLastName());
        profile.setBio(profileDTO.getBio());
        profile.setAvatarUrl(profileDTO.getAvatarUrl());
        profile.setLocation(profileDTO.getLocation());
        profile.setBirthDate(profileDTO.getBirthDate());

        return profileRepository.save(profile);
    }
}
