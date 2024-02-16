package com.ratz.CardFlopGame.services;

import com.ratz.CardFlopGame.entity.Role;

import java.util.Optional;


public interface RoleService {

    Optional<Role> getRoleByPlayerId(Long playerId);
}
