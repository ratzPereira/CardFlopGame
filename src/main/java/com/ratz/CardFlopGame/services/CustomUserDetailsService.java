package com.ratz.CardFlopGame.services;

import com.ratz.CardFlopGame.entity.Player;
import com.ratz.CardFlopGame.entity.UserPrincipal;
import com.ratz.CardFlopGame.repository.PlayerRepository;
import com.ratz.CardFlopGame.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PlayerRepository playerRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Player player = playerRepository.findByEmail(email);

        log.info("User found by email: {}", email);

        if (player == null) throw new UsernameNotFoundException("User not found");
        else return new UserPrincipal(player, roleRepository.getRoleByPlayerId(player.getId()).getPermission());
    }
}
