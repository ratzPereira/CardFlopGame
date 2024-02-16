package com.ratz.CardFlopGame.services;


import com.ratz.CardFlopGame.DTO.PlayerDTO;
import com.ratz.CardFlopGame.entity.Player;

public interface PlayerService {

    PlayerDTO createPlayer(Player player);
    
}
