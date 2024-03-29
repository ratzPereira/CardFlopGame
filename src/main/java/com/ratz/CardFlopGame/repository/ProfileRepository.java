package com.ratz.CardFlopGame.repository;

import com.ratz.CardFlopGame.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile getProfileByPlayerId(Long id);

}
