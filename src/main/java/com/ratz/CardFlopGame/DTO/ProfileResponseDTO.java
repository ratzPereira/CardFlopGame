package com.ratz.CardFlopGame.DTO;

import com.ratz.CardFlopGame.entity.Friendship;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ProfileResponseDTO {

    private String firstName;
    private String lastName;
    private String bio;
    private String avatarUrl;
    private String location;
    private LocalDate birthDate;
    private int wins;
    private int losses;

    //  Player
    private String username;
    private LocalDateTime createdAt;
    private Set<Friendship> friends;

}
