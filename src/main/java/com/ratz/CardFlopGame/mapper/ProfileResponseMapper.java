package com.ratz.CardFlopGame.mapper;

import com.ratz.CardFlopGame.DTO.ProfileResponseDTO;
import com.ratz.CardFlopGame.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileResponseMapper {

    ProfileResponseMapper INSTANCE = Mappers.getMapper(ProfileResponseMapper.class);

    ProfileResponseDTO profileToProfileDTO(Profile profile);

    Profile profileDTOToProfile(ProfileResponseDTO profileDTO);
}
