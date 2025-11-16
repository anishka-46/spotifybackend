package com.spotifyClone.spotifyBackend.features.authentication.services;

import com.spotifyClone.spotifyBackend.dom.userHistory;
import com.spotifyClone.spotifyBackend.dom.userLikedSongs;
import com.spotifyClone.spotifyBackend.dom.userPlaylist;
import com.spotifyClone.spotifyBackend.features.authentication.controller.LoginRes;
import com.spotifyClone.spotifyBackend.features.authentication.controller.SignupReq;
import com.spotifyClone.spotifyBackend.features.authentication.repository.UserRepository;
import com.spotifyClone.spotifyBackend.model.users;
import com.spotifyClone.spotifyBackend.utils.Bcrypt;
import com.spotifyClone.spotifyBackend.utils.Uuid;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;

@Service
public class AuthService {

    @Value("${JWT_SECRET_KEY}")
    private String JWT_SECRET_KEY;

    @Autowired
    private UserRepository userRepository;

    public String isLoggedIn(String token, String JWT_SECRET_KEY){

        try {
            Map<String, Object> claims = Jwts.parserBuilder()
                                            .setSigningKey(JWT_SECRET_KEY.getBytes())
                                            .build()
                                            .parseClaimsJws(token)
                                            .getBody();
            return claims.get("username").toString();
        }
        catch (Exception e){
            return "";
        }

    }

    public Object login(String username, String password) {

        Optional<users> fetchUser =  userRepository.findFirstByUsername(username);
        if(fetchUser.isPresent()){
            if(new Bcrypt().validateHash(password, fetchUser.get().getPassword())){
                Map<String, Object> mp = new HashMap<>();
                mp.put("username", username);
                Key key = new SecretKeySpec(JWT_SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
                String token = Jwts.builder()
                                .setClaims(mp)
                                .setIssuedAt(new Date())
                                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*24*10) )
                                .signWith(key)
                                .compact();
                return new LoginRes("logged",token);
            }
            else return "Incorrect email/password";
        }
        else return "Incorrect email/password";
    }

    public String signup(SignupReq req) {

        Optional<users> fetchUser = userRepository.findFirstByUsername(req.getUsername());
        if(fetchUser.isPresent()){
            return "Email Already Registered";
        }
        String uuid = new Uuid().generateUUID();
        String hashedPassword = new Bcrypt().generateHash(req.getPassword());
        List<userPlaylist> temp = new ArrayList<>();
        List<userHistory> temp1 = new ArrayList<>();
        List<userLikedSongs> temp2 = new ArrayList<>();
        userRepository.save(new users(uuid, req.getUsername(), req.getProfileName(), hashedPassword, temp, temp1, temp2, ""));

        return "You are registered";
    }
}
