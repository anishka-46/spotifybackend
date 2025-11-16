package com.spotifyClone.spotifyBackend.features.userActions;

public class AddSongToPlaylistReq {
    private String _id;
    private String username;
    private String songId;
    private String songName;
    private String songImageUrl;

    public AddSongToPlaylistReq() {
    }

    public AddSongToPlaylistReq(String _id, String username, String songId, String songName, String songImageUrl) {
        this._id = _id;
        this.username = username;
        this.songId = songId;
        this.songName = songName;
        this.songImageUrl = songImageUrl;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
