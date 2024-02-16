package com.ratz.CardFlopGame.services.Impl;

import com.ratz.CardFlopGame.DTO.PlayerDTO;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.entity.Role;
import com.ratz.CardFlopGame.exception.ApiException;
import com.ratz.CardFlopGame.mapper.PlayerMapper;
import com.ratz.CardFlopGame.repository.PlayerRepository;
import com.ratz.CardFlopGame.repository.RoleRepository;
import com.ratz.CardFlopGame.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ratz.CardFlopGame.enums.RoleType.ROLE_PLAYER;

@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final RoleRepository roleRepository;
    private final PlayerRepository playerRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public PlayerDTO createPlayer(Player player) {

        // Check the email is unique
        if (playerRepository.existsByEmail(player.getEmail())) {
            throw new ApiException("Email already exists");
        }

        // Check the username is unique
        if (playerRepository.existsByUsername(player.getUsername())) {
            throw new ApiException("Username already exists");
        }

        //save player
        try {

            // Add role to player
            Role playerRole = roleRepository.findByName(ROLE_PLAYER.name())
                    .orElseThrow(() -> new ApiException("Role not found"));

            player.getRoles().add(playerRole);

            // Encode password
            player.setPassword(encoder.encode(player.getPassword()));

            // TODO send verification email
            player.setUsingMfa(false);
            player.setEnabled(true);
            player.setNotLocked(true);

            // Convert and Return the newly created player
            Player newPlayer = playerRepository.save(player);
            return PlayerMapper.INSTANCE.playerToPlayerDTO(newPlayer);


            // If errors, throw exception with proper message
        } catch (Exception e) {
            throw new ApiException("Error creating player");
        }
    }

    @Override
    public PlayerDTO getPlayerByEmail(String email) {
        Player player = playerRepository.findByEmail(email);
        return PlayerMapper.INSTANCE.playerToPlayerDTO(player);
    }
}
