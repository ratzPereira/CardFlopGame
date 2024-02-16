package com.ratz.CardFlopGame.repository;

import com.ratz.CardFlopGame.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
