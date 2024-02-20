package com.ratz.CardFlopGame.service;

import com.ratz.CardFlopGame.DTO.PlayerDTO;
import com.ratz.CardFlopGame.DTO.RegisterFormDTO;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.entity.Role;
import com.ratz.CardFlopGame.repository.PlayerRepository;
import com.ratz.CardFlopGame.repository.RoleRepository;
import com.ratz.CardFlopGame.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.ratz.CardFlopGame.enums.RoleType.ROLE_PLAYER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @MockBean
    private PlayerRepository playerRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private BCryptPasswordEncoder encoder;

    @Captor
    private ArgumentCaptor<Player> playerArgumentCaptor;

    @BeforeEach
    void setup() {
        Player player = new Player();
        player.setEmail("test@example.com");
        player.setUsername("testUser");
        player.setPassword("password");

        when(playerRepository.findByEmail(anyString())).thenReturn(player);
    }

    @Test
    void whenGetPlayerByEmail_thenPlayerShouldBeFound() {
        String email = "test@example.com";
        Player found = playerService.getPlayerByEmail(email);

        assertThat(found.getEmail()).isEqualTo(email);
    }

    @Test
    void whenCreatePlayer_thenPlayerShouldBeSaved() {

        ArgumentCaptor<Player> playerArgumentCaptor = ArgumentCaptor.forClass(Player.class);

        RegisterFormDTO registerFormDTO = new RegisterFormDTO();
        registerFormDTO.setEmail("test@example.com");
        registerFormDTO.setUsername("testUser");
        registerFormDTO.setPassword("password");

        Role playerRole = new Role();
        playerRole.setName(ROLE_PLAYER.name());

        Player player = new Player();
        player.setEmail(registerFormDTO.getEmail());
        player.setUsername(registerFormDTO.getUsername());
        player.setPassword(registerFormDTO.getPassword());
        player.getRoles().add(playerRole);

        when(roleRepository.findByName(ROLE_PLAYER.name())).thenReturn(Optional.of(playerRole));
        when(encoder.encode(anyString())).thenReturn("encodedPassword");
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        PlayerDTO savedPlayer = playerService.createPlayer(registerFormDTO);

        assertThat(savedPlayer.getEmail()).isEqualTo(registerFormDTO.getEmail());
        assertThat(savedPlayer.getUsername()).isEqualTo(registerFormDTO.getUsername());
        verify(playerRepository).save(playerArgumentCaptor.capture());
        assertThat(playerArgumentCaptor.getValue().getPassword()).isEqualTo("encodedPassword");
    }


}
