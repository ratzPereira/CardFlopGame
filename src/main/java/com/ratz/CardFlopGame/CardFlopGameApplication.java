package com.ratz.CardFlopGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CardFlopGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardFlopGameApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        int BCRYPT_STRENGTH = 12;
        return new BCryptPasswordEncoder(BCRYPT_STRENGTH);
    }
}
