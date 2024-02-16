package com.ratz.CardFlopGame.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "players")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    private boolean enabled;
    private boolean notLocked;
    private boolean usingMfa;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "player")
    private Set<Friendship> friends;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, optional = false)
    private Profile profile;

}
