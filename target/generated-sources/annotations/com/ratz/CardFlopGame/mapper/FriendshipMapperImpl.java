package com.ratz.CardFlopGame.mapper;

import com.ratz.CardFlopGame.DTO.FriendshipDTO;
import com.ratz.CardFlopGame.entity.Friendship;
import com.ratz.CardFlopGame.entity.Player;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-20T00:06:26-0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class FriendshipMapperImpl implements FriendshipMapper {

    @Override
    public Friendship friendshipDTOToFriendship(FriendshipDTO friendshipDTO) {
        if ( friendshipDTO == null ) {
            return null;
        }

        Friendship friendship = new Friendship();

        friendship.setFriendshipDate( friendshipDTO.getFriendshipDate() );
        friendship.setAccepted( friendshipDTO.isAccepted() );
        friendship.setBlocked( friendshipDTO.isBlocked() );

        return friendship;
    }

    @Override
    public FriendshipDTO friendshipToFriendshipDTO(Friendship friendship) {
        if ( friendship == null ) {
            return null;
        }

        FriendshipDTO friendshipDTO = new FriendshipDTO();

        friendshipDTO.setFriendId( friendshipFriendId( friendship ) );
        friendshipDTO.setFriendUsername( friendshipFriendUsername( friendship ) );
        friendshipDTO.setAccepted( friendship.isAccepted() );
        friendshipDTO.setBlocked( friendship.isBlocked() );
        friendshipDTO.setFriendshipDate( friendship.getFriendshipDate() );

        return friendshipDTO;
    }

    private Long friendshipFriendId(Friendship friendship) {
        if ( friendship == null ) {
            return null;
        }
        Player friend = friendship.getFriend();
        if ( friend == null ) {
            return null;
        }
        Long id = friend.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String friendshipFriendUsername(Friendship friendship) {
        if ( friendship == null ) {
            return null;
        }
        Player friend = friendship.getFriend();
        if ( friend == null ) {
            return null;
        }
        String username = friend.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
