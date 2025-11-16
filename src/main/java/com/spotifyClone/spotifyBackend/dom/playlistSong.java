package com.spotifyClone.spotifyBackend.dom;

public class playlistSong {
    private String songId;
    private String songName;
    private String songImageUrl;

    public playlistSong() {
    }

    public playlistSong(String songId, String songImageUrl, String songName) {
        this.songId = songId;
        this.songImageUrl = songImageUrl;
        this.songName = songName;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongImageUrl() {
        return songImageUrl;
    }

    public void setSongImageUrl(String songImageUrl) {
        this.songImageUrl = songImageUrl;
    }
}
