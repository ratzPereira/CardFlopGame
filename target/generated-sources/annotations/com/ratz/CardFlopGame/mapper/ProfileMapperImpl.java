package com.ratz.CardFlopGame.mapper;

import com.ratz.CardFlopGame.DTO.ProfileResponseDTO;
import com.ratz.CardFlopGame.entity.Profile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-19T09:39:44-0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileResponseDTO profileToProfileDTO(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO();

        profileResponseDTO.setFirstName( profile.getFirstName() );
        profileResponseDTO.setLastName( profile.getLastName() );
        profileResponseDTO.setBio( profile.getBio() );
        profileResponseDTO.setAvatarUrl( profile.getAvatarUrl() );
        profileResponseDTO.setLocation( profile.getLocation() );
        profileResponseDTO.setBirthDate( profile.getBirthDate() );
        profileResponseDTO.setWins( profile.getWins() );
        profileResponseDTO.setLosses( profile.getLosses() );

        return profileResponseDTO;
    }

    @Override
    public Profile profileDTOToProfile(ProfileResponseDTO profileDTO) {
        if ( profileDTO == null ) {
            return null;
        }

        Profile profile = new Profile();

        profile.setFirstName( profileDTO.getFirstName() );
        profile.setLastName( profileDTO.getLastName() );
        profile.setBio( profileDTO.getBio() );
        profile.setAvatarUrl( profileDTO.getAvatarUrl() );
        profile.setLocation( profileDTO.getLocation() );
        profile.setBirthDate( profileDTO.getBirthDate() );
        profile.setWins( profileDTO.getWins() );
        profile.setLosses( profileDTO.getLosses() );

        return profile;
    }
}
