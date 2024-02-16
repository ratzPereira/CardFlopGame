package com.ratz.CardFlopGame.services.Impl;

import com.ratz.CardFlopGame.entity.Role;
import com.ratz.CardFlopGame.repository.RoleRepository;
import com.ratz.CardFlopGame.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> getRoleByPlayerId(Long playerId) {
        return roleRepository.getRoleByPlayerId(playerId);
    }
}
