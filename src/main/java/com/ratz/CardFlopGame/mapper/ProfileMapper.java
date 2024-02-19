package com.ratz.CardFlopGame.mapper;

import com.ratz.CardFlopGame.DTO.ProfileDTO;
import com.ratz.CardFlopGame.entity.Profile;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

    ProfileDTO profileToProfileDTO(Profile profile);

    Profile profileDTOToProfile(ProfileDTO profileDTO);

    // Método auxiliar para setar manualmente o Player e outros campos necessários
    @AfterMapping
    default void setPlayerAndOtherFields(ProfileDTO profileDTO, @MappingTarget Profile profile) {
        // Este método ficaria vazio se a lógica de associação não for realizada aqui
        // A associação do Player é feita no serviço ou controlador, como você já tem
    }
}
