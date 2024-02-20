package com.ratz.CardFlopGame.services;


import com.ratz.CardFlopGame.DTO.PlayerDTO;
import com.ratz.CardFlopGame.DTO.RegisterFormDTO;
import com.ratz.CardFlopGame.entity.Player;

public interface PlayerService {

    PlayerDTO createPlayer(RegisterFormDTO registerFormDTO);

    Player getPlayerByEmail(String email);

    Player getPlayerById(Long id);

    Player getPlayerByUsername(String username);

}
