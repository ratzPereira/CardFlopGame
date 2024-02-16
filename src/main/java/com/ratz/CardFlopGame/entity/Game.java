package com.ratz.CardFlopGame.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Player playerOne;

    @ManyToOne
    private Player playerTwo;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String winnerId;

    private int totalMoves;
    // Pensar em mais coisas para adicionar aqui por exemplo o deck usado de cada 1, a vida restante etc
}
