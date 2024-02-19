package com.ratz.CardFlopGame.services;

import com.ratz.CardFlopGame.DTO.ProfileDTO;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.entity.Profile;

public interface ProfileService {

    Profile getProfileByPlayerId(Long id);

    Profile createProfile(ProfileDTO profileDTO, Player player);

    Profile updateProfile(ProfileDTO profileDTO, Player player);
}
