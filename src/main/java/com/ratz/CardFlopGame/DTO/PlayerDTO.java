package com.ratz.CardFlopGame.DTO;

import com.ratz.CardFlopGame.entity.Deck;
import com.ratz.CardFlopGame.entity.Friendship;
import com.ratz.CardFlopGame.entity.Profile;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class PlayerDTO {

    private Long id;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private boolean notLocked;
    private boolean usingMfa;
    private LocalDateTime createdAt;

    //TODO ver se mando isto
    private Set<Deck> decks;
    private Set<Friendship> friends;
    private Profile profile;

}
