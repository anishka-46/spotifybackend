package com.spotifyClone.spotifyBackend.features.userActions;

import com.spotifyClone.spotifyBackend.dom.playlistSong;
import com.spotifyClone.spotifyBackend.dom.userLikedSongs;
import com.spotifyClone.spotifyBackend.dom.userPlaylist;
import com.spotifyClone.spotifyBackend.model.users;
import com.spotifyClone.spotifyBackend.utils.Uuid;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserActionsServices {

    @Autowired
    private UserActionsRepository userActionsRepository;
    @Value("${JWT_SECRET_KEY}")
    private String JWT_SECRET_KEY;

    public List<userPlaylist> getUserPlaylists(String username) {
        Optional<users> fetchUser = userActionsRepository.findFirstByUsername(username);
        List<userPlaylist> temp = new ArrayList<>();
        if(fetchUser.isPresent()) {
            return fetchUser.get().getPlaylist();
        }else{
            return temp;
        }
    }

    public List<userLikedSongs> getUserLikedSongs(String username) {
        Optional<users> fetchUser = userActionsRepository.findFirstByUsername(username);
        List<userLikedSongs> temp = new ArrayList<>();
        if(fetchUser.isPresent()) {
            return fetchUser.get().getLikedSongs();
        }else{
            return temp;
        }
    }

    public String removeLikedSong(String username, String songId) {
        Optional<users> fetchUser = userActionsRepository.findFirstByUsername(username);
        if(fetchUser.isPresent()) {
            List<userLikedSongs> newLikedSongs = new ArrayList<>();
            for(int i=0;i<fetchUser.get().getLikedSongs().size(); i++){
                if(!fetchUser.get().getLikedSongs().get(i).getSongId().equals(songId)){
                    newLikedSongs.add(fetchUser.get().getLikedSongs().get(i));
                }
            }
            fetchUser.get().setLikedSongs(newLikedSongs);
            userActionsRepository.save(fetchUser.get());
            return "Removed from library Successfully";

        }else{
            return "No User found";
        }

    }

    public String likeSong(UsernameSongReq req) {

        Optional<users> fetchUser = userActionsRepository.findFirstByUsername(req.getUsername());
        if(fetchUser.isPresent()) {
            List<userLikedSongs> newLikedSongs = fetchUser.get().getLikedSongs();
            newLikedSongs.add(new userLikedSongs(req.getSongId(), req.getSongName(), req.getSongImageUrl()));
            fetchUser.get().setLikedSongs(newLikedSongs);
            userActionsRepository.save(fetchUser.get());
            return "Added to library Successfully";

        }else{
            return "No User found";
        }
    }

    public userPlaylist getUserPlaylist(String id, String token) {
        Map<String, Object> claims = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username =  claims.get("username").toString();
        Optional<users> fetchUser = userActionsRepository.findFirstByUsername(username);
        if(fetchUser.isPresent()) {
            for(int i=0;i<fetchUser.get().getPlaylist().size();i++){
                if(fetchUser.get().getPlaylist().get(i).get_id().equals(id)){
                    return fetchUser.get().getPlaylist().get(i);
                }
            }
        }
        return null;
    }

    public String createPlaylist(UsernamePlaylistNameReq req) {
        Optional<users> fetchUser = userActionsRepository.findFirstByUsername(req.getUsername());
        if(fetchUser.isPresent()){
            List<userPlaylist> playlist = fetchUser.get().getPlaylist();
            String uuid = new Uuid().generateUUID();
            playlist.add(new userPlaylist(uuid, new ArrayList<>(), req.getPlaylistName()));
            fetchUser.get().setPlaylist(playlist);
            userActionsRepository.save(fetchUser.get());
            return "Created Playlist Succesfully";
        }
        return "No User found";

    }

    public String deletePlaylist(UsernameIdReq req) {
        Optional<users> fetchUser = userActionsRepository.findFirstByUsername(req.getUsername());
        if(fetchUser.isPresent()){
            List<userPlaylist> playlist = fetchUser.get().getPlaylist();
            List<userPlaylist> newPlaylist = new ArrayList<>();
            for(userPlaylist play : playlist){
                if(!play.get_id().equals(req.get_id())){
                    newPlaylist.add(play);
                }
            }
            fetchUser.get().setPlaylist(newPlaylist);
            userActionsRepository.save(fetchUser.get());
            return "Playlist Deleted Successfully";
        }
        return "No User found";
    }

    public String addSongToPlaylist(AddSongToPlaylistReq req) {
        Optional<users> fetchUser = userActionsRepository.findFirstByUsername(req.getUsername());
        if(fetchUser.isPresent()){
            List<userPlaylist> playlist = fetchUser.get().getPlaylist();
            for(userPlaylist play : playlist){
                if(play.get_id().equals(req.get_id())){
                    boolean foundSong = false;
                    for(playlistSong song: play.getPlaylistSongs()){
                        if(song.getSongId().equals(req.get_id())){
                            foundSong = true;
                            break;
                        }
                    }
                    if(!foundSong){
                        List<playlistSong> newPlaylistSongs = play.getPlaylistSongs();
                        newPlaylistSongs.add(new playlistSong(req.getSongId(), req.getSongImageUrl(), req.getSongName()));
                        play.setPlaylistSongs(newPlaylistSongs);
                        userActionsRepository.save(fetchUser.get());
                        return "Added to playlist Successfully";
                    }
                    return "Song Already in Playlist";
                }
            }
        }
        return "No User found";

    }

    public String deleteSongFromPlaylist(DeleteSongFromPlaylist req) {
        Optional<users> fetchUser = userActionsRepository.findFirstByUsername(req.getUsername());
        if(fetchUser.isPresent()){
            List<userPlaylist> playlist = fetchUser.get().getPlaylist();
            for(userPlaylist play : playlist){
                if(play.get_id().equals(req.getPlaylistId())){
                    List<playlistSong> newPlaylistSongs = new ArrayList<>();
                    boolean foundSong = false;
                    for(playlistSong song: play.getPlaylistSongs()){
                        if(!song.getSongId().equals(req.getSongId())){
                            newPlaylistSongs.add(song);
                        }
                        else {
                            foundSong = true;
                        }
                    }
                    if(foundSong){
                        play.setPlaylistSongs(newPlaylistSongs);
                        userActionsRepository.save(fetchUser.get());
                        return "Deleted from playlist Successfully";
                    }
                    return "Song  Not in Playlist";
                }
            }
        }
        return "No User found";

    }
}
