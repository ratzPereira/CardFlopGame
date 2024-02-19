package com.ratz.CardFlopGame.services;

import com.ratz.CardFlopGame.entity.Profile;

public interface ProfileService {

    Profile getProfileByPlayerId(Long id);
}
