package com.spotifyClone.spotifyBackend.features.userActions;

public class DeleteSongFromPlaylist {
    private String username;
    private String songId;
    private String playlistId;

    public DeleteSongFromPlaylist() {
    }

    public DeleteSongFromPlaylist(String username, String songId, String playlistId) {
        this.username = username;
        this.songId = songId;
        this.playlistId = playlistId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }
}
