package com.ratz.CardFlopGame.DTO;

import com.ratz.CardFlopGame.entity.Player;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FriendshipDTO {

    private LocalDateTime friendshipDate;
    private Player player;
    private Player friend;
}
