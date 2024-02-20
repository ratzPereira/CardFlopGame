package com.ratz.CardFlopGame.service;

import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.repository.PlayerRepository;
import com.ratz.CardFlopGame.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    void setup() {
        
        Player player = new Player();
        player.setEmail("test@example.com");
        player.setUsername("testUser");
        player.setPassword("password");
        playerRepository.save(player);
    }

    @Test
    void whenGetPlayerByEmail_thenPlayerShouldBeFound() {
        String email = "test@example.com";
        Player found = playerService.getPlayerByEmail(email);

        assertThat(found.getEmail()).isEqualTo(email);
    }
}
