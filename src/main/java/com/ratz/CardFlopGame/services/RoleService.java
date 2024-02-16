package com.ratz.CardFlopGame.services;

import com.ratz.CardFlopGame.entity.Role;


public interface RoleService {

    Role getRoleByPlayerId(Long playerId);
}
