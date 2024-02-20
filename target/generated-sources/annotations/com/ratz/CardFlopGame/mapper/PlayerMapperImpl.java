package com.ratz.CardFlopGame.mapper;

import com.ratz.CardFlopGame.DTO.PlayerDTO;
import com.ratz.CardFlopGame.entity.Deck;
import com.ratz.CardFlopGame.entity.Friendship;
import com.ratz.CardFlopGame.entity.Player;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-20T00:06:26-0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class PlayerMapperImpl implements PlayerMapper {

    @Override
    public PlayerDTO playerToPlayerDTO(Player player) {
        if ( player == null ) {
            return null;
        }

        PlayerDTO playerDTO = new PlayerDTO();

        playerDTO.setId( player.getId() );
        playerDTO.setUsername( player.getUsername() );
        playerDTO.setEmail( player.getEmail() );
        playerDTO.setEnabled( player.isEnabled() );
        playerDTO.setNotLocked( player.isNotLocked() );
        playerDTO.setUsingMfa( player.isUsingMfa() );
        playerDTO.setCreatedAt( player.getCreatedAt() );
        Set<Deck> set = player.getDecks();
        if ( set != null ) {
            playerDTO.setDecks( new HashSet<Deck>( set ) );
        }
        Set<Friendship> set1 = player.getFriends();
        if ( set1 != null ) {
            playerDTO.setFriends( new HashSet<Friendship>( set1 ) );
        }
        playerDTO.setProfile( player.getProfile() );

        return playerDTO;
    }

    @Override
    public Player playerDTOToPlayer(PlayerDTO playerDTO) {
        if ( playerDTO == null ) {
            return null;
        }

        Player player = new Player();

        player.setId( playerDTO.getId() );
        player.setUsername( playerDTO.getUsername() );
        player.setEmail( playerDTO.getEmail() );
        player.setEnabled( playerDTO.isEnabled() );
        player.setNotLocked( playerDTO.isNotLocked() );
        player.setUsingMfa( playerDTO.isUsingMfa() );
        player.setCreatedAt( playerDTO.getCreatedAt() );
        Set<Deck> set = playerDTO.getDecks();
        if ( set != null ) {
            player.setDecks( new HashSet<Deck>( set ) );
        }
        Set<Friendship> set1 = playerDTO.getFriends();
        if ( set1 != null ) {
            player.setFriends( new HashSet<Friendship>( set1 ) );
        }
        player.setProfile( playerDTO.getProfile() );

        return player;
    }
}
