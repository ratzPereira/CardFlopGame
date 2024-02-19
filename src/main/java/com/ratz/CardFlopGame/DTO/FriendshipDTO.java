package com.ratz.CardFlopGame.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendshipDTO {

    private Long friendId;
    private String friendUsername;
    private boolean accepted;
    private boolean blocked;
    private LocalDateTime friendshipDate;
}
