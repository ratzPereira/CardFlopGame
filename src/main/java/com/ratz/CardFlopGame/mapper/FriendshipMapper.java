package com.ratz.CardFlopGame.mapper;

import com.ratz.CardFlopGame.DTO.FriendshipDTO;
import com.ratz.CardFlopGame.entity.Friendship;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FriendshipMapper {

    FriendshipMapper INSTANCE = Mappers.getMapper(FriendshipMapper.class);

    FriendshipDTO friendshipToFriendshipDTO(Friendship friendship);

    Friendship friendshipDTOToFriendship(FriendshipDTO friendshipDTO);

}
