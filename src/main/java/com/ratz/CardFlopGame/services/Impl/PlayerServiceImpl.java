package com.ratz.CardFlopGame.services.Impl;

import com.ratz.CardFlopGame.DTO.PlayerDTO;
import com.ratz.CardFlopGame.DTO.RegisterFormDTO;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.entity.Role;
import com.ratz.CardFlopGame.exception.ApiException;
import com.ratz.CardFlopGame.mapper.PlayerMapper;
import com.ratz.CardFlopGame.repository.PlayerRepository;
import com.ratz.CardFlopGame.repository.RoleRepository;
import com.ratz.CardFlopGame.services.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.ratz.CardFlopGame.enums.RoleType.ROLE_PLAYER;

@RequiredArgsConstructor
@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    private final RoleRepository roleRepository;
    private final PlayerRepository playerRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public PlayerDTO createPlayer(RegisterFormDTO registerFormDTO) {

        log.info("Attempting to register a new player with username: {}", registerFormDTO.getUsername());
        // Check the email is unique
        if (playerRepository.existsByEmail(registerFormDTO.getEmail())) {
            throw new ApiException("Email already exists");
        }

        // Check the username is unique
        if (playerRepository.existsByUsername(registerFormDTO.getUsername())) {
            throw new ApiException("Username already exists");
        }

        //save player
        try {

            Player player = new Player();
            // Add role to player
            Role playerRole = roleRepository.findByName(ROLE_PLAYER.name())
                    .orElseThrow(() -> new ApiException("Role not found"));

            player.getRoles().add(playerRole);
            player.setEmail(registerFormDTO.getEmail());
            player.setUsername(registerFormDTO.getUsername());

            // Encode password
            player.setPassword(encoder.encode(registerFormDTO.getPassword()));

            // TODO send verification email
            player.setUsingMfa(false);
            player.setEnabled(true);
            player.setNotLocked(true);

            // Convert and Return the newly created player
            playerRepository.save(player);
            return PlayerMapper.INSTANCE.playerToPlayerDTO(player);


            // If errors, throw exception with proper message
        } catch (Exception e) {
            throw new ApiException("Error creating player");
        }
    }

    @Override
    public Player getPlayerByEmail(String email) {
        log.debug("Fetching player by email: {}", email);
        return playerRepository.findByEmail(email);
    }

    @Override
    public Player getPlayerById(Long id) {
        log.debug("Fetching player by id: {}", id);
        return playerRepository.findById(id).orElseThrow(() -> new ApiException("Player not found"));
    }

    @Override
    public Player getPlayerByUsername(String username) {
        log.debug("Fetching player by username: {}", username);
        return playerRepository.findByUsername(username).orElseThrow(() -> new ApiException("Player not found"));
    }
}
