package com.ratz.CardFlopGame.services;

import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.repository.PlayerRepository;
import com.ratz.CardFlopGame.services.Impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final PlayerRepository playerRepository;

    @Autowired
    public CustomUserDetailsService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Player player = playerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Aqui, você pode mapear seu objeto Player para um objeto UserDetails.
        // Por exemplo, usando uma classe UserDetailsImpl que implementa UserDetails
        return new UserDetailsImpl(player);
    }
}
