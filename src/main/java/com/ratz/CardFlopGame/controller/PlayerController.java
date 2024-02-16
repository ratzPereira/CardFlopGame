package com.ratz.CardFlopGame.controller;

import com.ratz.CardFlopGame.DTO.LoginFormDTO;
import com.ratz.CardFlopGame.DTO.PlayerDTO;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.entity.UserPrincipal;
import com.ratz.CardFlopGame.mapper.PlayerMapper;
import com.ratz.CardFlopGame.provider.TokenProvider;
import com.ratz.CardFlopGame.response.HttpResponse;
import com.ratz.CardFlopGame.services.PlayerService;
import com.ratz.CardFlopGame.services.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/player")
@Slf4j
public class PlayerController {

    private final PlayerService playerService;
    private final RoleService roleService;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;


    @PostMapping("/register")
    public ResponseEntity<HttpResponse> createPlayer(@RequestBody @Valid Player player) {

        PlayerDTO playerDTO = playerService.createPlayer(player);

        return ResponseEntity.created(getURI())
                .body(HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(Map.of("player", playerDTO))
                        .message("Player created")
                        .statusCode(HttpStatus.CREATED.value())
                        .httpStatus(HttpStatus.CREATED)
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<HttpResponse> login(@RequestBody @Valid LoginFormDTO loginForm) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()));
        PlayerDTO playerDTO = playerService.getPlayerByEmail(loginForm.getEmail());

        return sendResponse(playerDTO);
    }

    private URI getURI() {
        return URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/player/get/<playerId>").toUriString());
    }

    private UserPrincipal getUserPrincipal(PlayerDTO playerDTO) {
        return new UserPrincipal(PlayerMapper.INSTANCE.playerDTOToPlayer(playerService.getPlayerByEmail(playerDTO.getEmail())), roleService.getRoleByPlayerId(playerDTO.getId()).getPermission());
    }

    private ResponseEntity<HttpResponse> sendResponse(PlayerDTO playerDTO) {

        return ResponseEntity.ok()
                .body(HttpResponse.builder()
                        .timeStamp(now().toString())
                        .data(Map.of("player", playerDTO,
                                "access_token", tokenProvider.createAccessToken(getUserPrincipal(playerDTO)),
                                "refresh_token", tokenProvider.createRefreshToken(getUserPrincipal(playerDTO))))
                        .message("User logged in")
                        .statusCode(HttpStatus.OK.value())
                        .httpStatus(HttpStatus.OK)
                        .build());
    }
}
