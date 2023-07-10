package com.example.project.controller;

import com.example.project.config.jwt.JwtTokenProvider;
import com.example.project.entity.Users;
import com.example.project.model.UserDetailCustom;
import com.example.project.request.SignInRequest;
import com.example.project.utill.GoogleTokenVerifier;
import com.example.project.utill.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author thangdt
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthResController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private GoogleTokenVerifier googleTokenVerifier;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/oauth2/{token}")
    public JwtResponse oauth2(@PathVariable("token") String tokenGG) {
        Users user = googleTokenVerifier.varifyIdToken(tokenGG);
        String token = tokenProvider.generateToken(authenticateAndGetUser(user.getEmail(), ""));
        return new JwtResponse(token, user);
    }

    @PostMapping("/sign-in")
    public JwtResponse signIn(@RequestBody SignInRequest request) {
        Users user = authenticateAndGetUser(request.getEmail(), request.getPassword()).getUser();
        String token = tokenProvider.generateToken(authenticateAndGetUser(request.getEmail(), request.getPassword()));
        return new JwtResponse(token, user);
    }

    private UserDetailCustom authenticateAndGetUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return (UserDetailCustom) authentication.getPrincipal();
    }

}
