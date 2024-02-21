package com.ratz.CardFlopGame.service;

import com.ratz.CardFlopGame.DTO.ProfileDTO;
import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.entity.Profile;
import com.ratz.CardFlopGame.exception.ApiException;
import com.ratz.CardFlopGame.exception.ProfileAlreadyExistsException;
import com.ratz.CardFlopGame.repository.ProfileRepository;
import com.ratz.CardFlopGame.services.ProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProfileServiceTest {

    @MockBean
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileService profileService;

    @Test
    void whenCreateProfile_thenProfileShouldBeSaved() {
        Player player = new Player();
        player.setId(1L);

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFirstName("John");
        profileDTO.setLastName("Doe");


        Profile profile = new Profile();
        profile.setPlayer(player);


        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        Profile createdProfile = profileService.createProfile(profileDTO, player);

        assertNotNull(createdProfile);
        assertEquals(player.getId(), createdProfile.getPlayer().getId());
        verify(profileRepository).save(any(Profile.class));
    }

    @Test
    void whenUpdateProfile_thenProfileShouldBeUpdated() {
        Player player = new Player();
        player.setId(1L);

        Profile existingProfile = new Profile();
        existingProfile.setPlayer(player);
        existingProfile.setFirstName("John");

        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFirstName("Jane");

        when(profileRepository.getProfileByPlayerId(player.getId())).thenReturn(existingProfile);
        when(profileRepository.save(any(Profile.class))).thenReturn(existingProfile);

        Profile updatedProfile = profileService.updateProfile(profileDTO, player);

        assertNotNull(updatedProfile);
        assertEquals("Jane", updatedProfile.getFirstName());
        verify(profileRepository).save(any(Profile.class));
    }

    @Test
    void whenGetProfileByPlayerId_thenProfileShouldBeReturned() {
        Long playerId = 1L;
        Profile profile = new Profile();
        profile.setPlayer(new Player());
        profile.getPlayer().setId(playerId);

        when(profileRepository.getProfileByPlayerId(playerId)).thenReturn(profile);

        Profile foundProfile = profileService.getProfileByPlayerId(playerId);

        assertNotNull(foundProfile);
        assertEquals(playerId, foundProfile.getPlayer().getId());
    }


    @Test
    void whenUpdateNonExistingProfile_thenThrowException() {
        Long playerId = 1L;
        Player player = new Player();
        player.setId(playerId);

        ProfileDTO profileDTO = new ProfileDTO();
        // Configuração do DTO

        when(profileRepository.getProfileByPlayerId(playerId)).thenReturn(null);

        assertThrows(ApiException.class, () -> profileService.updateProfile(profileDTO, player),
                "Expected updateProfile to throw, but it didn't");
    }

    @Test
    void whenCreateProfileForPlayerWithExistingProfile_thenThrowException() {
        Player player = new Player();
        player.setId(1L);

        Profile existingProfile = new Profile();

        when(profileRepository.getProfileByPlayerId(player.getId())).thenReturn(existingProfile);

        ProfileDTO profileDTO = new ProfileDTO();

        assertThrows(ProfileAlreadyExistsException.class, () -> profileService.createProfile(profileDTO, player),
                "Expected ProfileAlreadyExistsException to be thrown due to existing profile");
    }

    @Test
    void whenCreateProfileForNonexistentPlayer_thenThrowException() {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setFirstName("Test");
        profileDTO.setLastName("User");
        // Assume que não há player com ID 999L
        Player nonexistentPlayer = new Player();
        nonexistentPlayer.setId(999L);

        when(profileRepository.findById(nonexistentPlayer.getId())).thenReturn(Optional.empty());

        assertThrows(ApiException.class, () -> profileService.createProfile(profileDTO, nonexistentPlayer),
                "Expected ApiException to be thrown due to nonexistent player");
    }

    @Test
    void whenUpdateProfileWithFullData_thenAllFieldsAreUpdated() {
        Long playerId = 1L;
        Player player = new Player();
        player.setId(playerId);

        Profile existingProfile = new Profile();
        existingProfile.setPlayer(player);
        existingProfile.setId(playerId);

        ProfileDTO updateData = new ProfileDTO();
        updateData.setFirstName("NewFirstName");
        updateData.setLastName("NewLastName");
        updateData.setBio("NewBio");
        updateData.setAvatarUrl("NewAvatarUrl");
        updateData.setLocation("NewLocation");
        updateData.setBirthDate(LocalDate.now());

        // Mock to return the existing profile when getProfileByPlayerId is called
        when(profileRepository.getProfileByPlayerId(player.getId())).thenReturn(existingProfile);

        // Mock to return the existing profile when save is called
        when(profileRepository.save(any(Profile.class))).thenReturn(existingProfile);

        Profile updatedProfile = profileService.updateProfile(updateData, player);

        assertNotNull(updatedProfile);
        assertEquals(updateData.getFirstName(), updatedProfile.getFirstName());
        assertEquals(updateData.getLastName(), updatedProfile.getLastName());
        assertEquals(updateData.getBio(), updatedProfile.getBio());
        assertEquals(updateData.getAvatarUrl(), updatedProfile.getAvatarUrl());
        assertEquals(updateData.getLocation(), updatedProfile.getLocation());
        assertEquals(updateData.getBirthDate(), updatedProfile.getBirthDate());

        // In this case, we are verifying that the save method was called with the updated profile
        verify(profileRepository).save(any(Profile.class));
    }
}
