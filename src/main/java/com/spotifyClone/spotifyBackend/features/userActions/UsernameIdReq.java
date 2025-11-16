package com.spotifyClone.spotifyBackend.features.userActions;

public class UsernameIdReq {
    private String username;
    private String _id;

    public UsernameIdReq() {
    }

    public UsernameIdReq(String username, String _id) {
        this.username = username;
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
