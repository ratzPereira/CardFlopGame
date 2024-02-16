package com.ratz.CardFlopGame.mapper;

import com.ratz.CardFlopGame.DTO.PlayerDTO;
import com.ratz.CardFlopGame.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    PlayerDTO playerToPlayerDTO(Player player);

    Player playerDTOToPlayer(PlayerDTO playerDTO);
}
