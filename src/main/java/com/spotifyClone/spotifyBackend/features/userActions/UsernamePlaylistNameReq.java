package com.spotifyClone.spotifyBackend.features.userActions;

public class UsernamePlaylistNameReq {
    private String username;
    private String playlistName;

    public UsernamePlaylistNameReq() {
    }

    public UsernamePlaylistNameReq(String username, String playlistName) {
        this.username = username;
        this.playlistName = playlistName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}
