package com.ratz.CardFlopGame.mapper;

import com.ratz.CardFlopGame.DTO.ProfileDTO;
import com.ratz.CardFlopGame.entity.Profile;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-20T00:06:26-0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public ProfileDTO profileToProfileDTO(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.setFirstName( profile.getFirstName() );
        profileDTO.setLastName( profile.getLastName() );
        profileDTO.setBio( profile.getBio() );
        profileDTO.setAvatarUrl( profile.getAvatarUrl() );
        profileDTO.setLocation( profile.getLocation() );
        profileDTO.setBirthDate( profile.getBirthDate() );

        return profileDTO;
    }

    @Override
    public Profile profileDTOToProfile(ProfileDTO profileDTO) {
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

        setPlayerAndOtherFields( profileDTO, profile );

        return profile;
    }
}
