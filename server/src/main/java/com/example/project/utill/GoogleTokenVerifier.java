package com.example.project.utill;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;

import com.example.project.entity.Users;
import com.example.project.request.ProcessOAuthPostLoginRequest;
import com.example.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

/**
 * @author thangdt
 */
@Component
public class GoogleTokenVerifier {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${client.id.google}")
    private String ClientIdGoogle;

    private  NetHttpTransport transport = new NetHttpTransport();
    private  JsonFactory jsonFactory = new GsonFactory();

    public Users varifyIdToken(String token) {
        String clientId = "test";
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(ClientIdGoogle))
                .build();
        GoogleIdToken idToken = null;
        try {
            idToken = verifier.verify(token);
            System.out.println(idToken);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (idToken != null) {
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = (String)payload.get("email");
            String fullName  = (String)payload.get("name");
            ProcessOAuthPostLoginRequest loginRequest = new ProcessOAuthPostLoginRequest(fullName, email);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, ""));
            SecurityContextHolder.getContext().setAuthentication(authentication);
           return  userService.processOAuthPostLogin(loginRequest);

        } else {
            throw new IllegalArgumentException("Invalid ID token");
        }
    }

}
