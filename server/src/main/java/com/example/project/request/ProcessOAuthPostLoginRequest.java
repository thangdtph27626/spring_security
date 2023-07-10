package com.example.project.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author thangdt
 */
@Setter
@Getter
@NoArgsConstructor
public class ProcessOAuthPostLoginRequest {

    private String fullName;
    private String gmail;

    public ProcessOAuthPostLoginRequest(String fullName, String gmail) {
        this.fullName = fullName;
        this.gmail = gmail;
    }
}
