package com.ratz.CardFlopGame.services.Impl;

import com.ratz.CardFlopGame.DTO.PlayerDTO;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.repository.RoleRepository;
import com.ratz.CardFlopGame.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public PlayerDTO createPlayer(Player player) {

        // Check the email is unique
        // Add role to user
        // TODO send verification email
        // Return the newly created user
        // If errors, throw exception with proper message
        return null;
    }
}
