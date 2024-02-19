package com.ratz.CardFlopGame.mapper;

import com.ratz.CardFlopGame.DTO.FriendshipDTO;
import com.ratz.CardFlopGame.entity.Friendship;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-19T09:39:44-0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class FriendshipMapperImpl implements FriendshipMapper {

    @Override
    public FriendshipDTO friendshipToFriendshipDTO(Friendship friendship) {
        if ( friendship == null ) {
            return null;
        }

        FriendshipDTO friendshipDTO = new FriendshipDTO();

        friendshipDTO.setFriendshipDate( friendship.getFriendshipDate() );
        friendshipDTO.setPlayer( friendship.getPlayer() );
        friendshipDTO.setFriend( friendship.getFriend() );

        return friendshipDTO;
    }

    @Override
    public Friendship friendshipDTOToFriendship(FriendshipDTO friendshipDTO) {
        if ( friendshipDTO == null ) {
            return null;
        }

        Friendship friendship = new Friendship();

        friendship.setFriendshipDate( friendshipDTO.getFriendshipDate() );
        friendship.setPlayer( friendshipDTO.getPlayer() );
        friendship.setFriend( friendshipDTO.getFriend() );

        return friendship;
    }
}
