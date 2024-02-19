package com.ratz.CardFlopGame.exception;

public class ProfileAlreadyExistsException extends ApiException {

    public ProfileAlreadyExistsException(Long id) {
        super("Profile already exists for player with id: " + id, "PROFILE_ALREADY_EXISTS");
    }
}
