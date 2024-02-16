package com.ratz.CardFlopGame.entity;

import com.ratz.CardFlopGame.enums.CardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CardType type;

    private int attackPoints;
    private int healthPoints;
}
