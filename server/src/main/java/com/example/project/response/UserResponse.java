package com.example.project.response;

import com.example.project.entity.Users;
import com.example.project.utill.Roles;
import lombok.Getter;
import lombok.Setter;

/**
 * @author thangdt
 */
@Getter
@Setter
public class UserResponse {

    private String id;

    private String fullName;

    private String phoneNumber;

    private String email;

    private boolean gender;

    private Roles role;

    private Long dateBirth;

    public UserResponse(Users user) {
        this.id = user.getId();
        this.fullName = user.getFullName();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.gender = user.isGender();
        this.role = user.getRole();
        this.dateBirth = user.getDateBirth();
    }
}
