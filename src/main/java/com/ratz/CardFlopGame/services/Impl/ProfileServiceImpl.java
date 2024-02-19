package com.ratz.CardFlopGame.services.Impl;

import com.ratz.CardFlopGame.DTO.ProfileDTO;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.entity.Profile;
import com.ratz.CardFlopGame.mapper.ProfileMapper;
import com.ratz.CardFlopGame.repository.ProfileRepository;
import com.ratz.CardFlopGame.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public Profile getProfileByPlayerId(Long id) {
        return profileRepository.getProfileByPlayerId(id);
    }

    @Override
    public Profile createProfile(ProfileDTO profileDTO, Player player) {

        Profile profile = ProfileMapper.INSTANCE.profileDTOToProfile(profileDTO);
        profile.setPlayer(player);
        return profileRepository.save(profile);
    }

    @Override
    public Profile updateProfile(ProfileDTO profileDTO, Player player) {

        Profile profile = profileRepository.getProfileByPlayerId(player.getId());
        profile.setFirstName(profileDTO.getFirstName());
        profile.setLastName(profileDTO.getLastName());
        profile.setBio(profileDTO.getBio());
        profile.setAvatarUrl(profileDTO.getAvatarUrl());
        profile.setLocation(profileDTO.getLocation());
        profile.setBirthDate(profileDTO.getBirthDate());

        return profileRepository.save(profile);
    }
}
