package com.spotifyClone.spotifyBackend.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Bcrypt {

    @Value("${SALT_ROUNDS}")
    private String SALT_ROUNDS;

    private final PasswordEncoder passwordEncoder;

    public Bcrypt() {
        this.passwordEncoder = new BCryptPasswordEncoder(11);
    }


    public String generateHash(String password){
        return passwordEncoder.encode(password);
    }

    public boolean validateHash(String password, String hashedPassword){
        return passwordEncoder.matches(password, hashedPassword);
    }

}
