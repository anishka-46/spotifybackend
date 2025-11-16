package com.spotifyClone.spotifyBackend.features.authentication.controller;

public class SignupReq {
    private String username;
    private String profileName;
    private String password;

    public SignupReq() {
    }

    public SignupReq(String username, String profileName, String password) {
        this.username = username;
        this.profileName = profileName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
