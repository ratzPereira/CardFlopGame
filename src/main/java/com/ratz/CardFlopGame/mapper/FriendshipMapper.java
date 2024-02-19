package com.ratz.CardFlopGame.mapper;

import com.ratz.CardFlopGame.DTO.FriendshipDTO;
import com.ratz.CardFlopGame.entity.Friendship;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FriendshipMapper {

    FriendshipMapper INSTANCE = Mappers.getMapper(FriendshipMapper.class);


    Friendship friendshipDTOToFriendship(FriendshipDTO friendshipDTO);

    @Mapping(source = "friend.id", target = "friendId")
    @Mapping(source = "friend.username", target = "friendUsername")
    FriendshipDTO friendshipToFriendshipDTO(Friendship friendship);

}
