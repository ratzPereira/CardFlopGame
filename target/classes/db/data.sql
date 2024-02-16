-- Roles
INSERT INTO roles (id, name, permission)
VALUES (1, 'ROLE_PLAYER', 'ACCESS_PLAYER');
INSERT INTO roles (id, name, permission)
VALUES (2, 'ROLE_ADMIN', 'ACCESS_ADMIN');

-- Players
INSERT INTO players (id, username, email, password, enabled, not_locked, using_mfa, created_at)
VALUES (1, 'player1', 'player1@example.com', 'password', true, true, false, now());
INSERT INTO players (id, username, email, password, enabled, not_locked, using_mfa, created_at)
VALUES (2, 'player2', 'player2@example.com', 'password', true, true, false, now());

-- Player Roles
INSERT INTO player_roles (player_id, role_id)
VALUES (1, 1);
INSERT INTO player_roles (player_id, role_id)
VALUES (2, 1);

-- Cards
INSERT INTO cards (id, name, type, attack_points, health_points)
VALUES (1, 'Card 1', 'FOGO', 5, 5);
INSERT INTO cards (id, name, type, attack_points, health_points)
VALUES (2, 'Card 2', 'AGUA', 4, 6);

-- Profiles
INSERT INTO profiles (id, first_name, last_name, bio, avatar_url, location, birth_date, wins, losses, player_id)
VALUES (1, 'John', 'Doe', 'Bio 1', '', 'Location 1', '2000-01-01', 0, 0, 1);
INSERT INTO profiles (id, first_name, last_name, bio, avatar_url, location, birth_date, wins, losses, player_id)
VALUES (2, 'Jane', 'Doe', 'Bio 2', '', 'Location 2', '2000-02-02', 0, 0, 2);

-- Decks
INSERT INTO decks (name, owner_id)
VALUES ('Deck 1', 1);
INSERT INTO decks (name, owner_id)
VALUES ('Deck 2', 2);

-- Associar cartas aos decks
INSERT INTO decks_cards (deck_id, cards_id)
VALUES (1, 1);
INSERT INTO decks_cards (deck_id, cards_id)
VALUES (1, 2);
INSERT INTO decks_cards (deck_id, cards_id)
VALUES (2, 1);
INSERT INTO decks_cards (deck_id, cards_id)
VALUES (2, 2);

-- Friendship
INSERT INTO friendships (friendship_date, accepted, player_id, friend_id)
VALUES (now(), true, 1, 2);
INSERT INTO friendships (friendship_date, accepted, player_id, friend_id)
VALUES (now(), true, 2, 1);

-- Games
INSERT INTO games (player_one_id, player_two_id, start_time, end_time, winner_id, total_moves)
VALUES (1, 2, now(), now(), '1', 10);
INSERT INTO games (player_one_id, player_two_id, start_time, end_time, winner_id, total_moves)
VALUES (2, 1, now(), now(), '2', 8);
