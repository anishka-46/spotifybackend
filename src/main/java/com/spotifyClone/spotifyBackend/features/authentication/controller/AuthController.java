package com.spotifyClone.spotifyBackend.features.authentication.controller;

import com.spotifyClone.spotifyBackend.features.authentication.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @Value("${SALT_ROUNDS}")
    private String SALT_ROUNDS;

    @Value("${JWT_SECRET_KEY}")
    private String JWT_SECRET_KEY;

    @GetMapping("/hello")
    public String hello(){
        return "Server is running";
    }

    @PostMapping("/isLoggedIn")
    public String isLoggedIn(@RequestBody IsLoggedInReq req){
        return authService.isLoggedIn(req.getToken(), JWT_SECRET_KEY);
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginReq req){
        return authService.login(req.getUsername(), req.getPassword());
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupReq req){
        return authService.signup(req);
    }

}
