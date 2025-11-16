package com.spotifyClone.spotifyBackend.utils;

import java.util.UUID;

public class Uuid {

    public String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
