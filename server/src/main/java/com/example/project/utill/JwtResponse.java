package com.example.project.utill;

import com.example.project.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author thangdt
 */

@Setter
@Getter
@NoArgsConstructor
public class JwtResponse {

    private String token;

    private String gmail;

    private String idUser;

    public JwtResponse(String token, Users users) {
        this.token = token;
        this.gmail = users.getEmail();
        this.idUser = users.getId();
    }
}
