package com.example.project.request;

import com.example.project.utill.Roles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * @author thangdt
 */

@Getter
@Setter
public class UserRequest {

    @NotEmpty
    private String fullName;

    @NotEmpty
    @Pattern(regexp = "(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}", message = "vui lòng nhập đúng định dạng điện thoại")
    private String phoneNumber;

    @NotEmpty
    @Email
    private String email;

    private boolean gender;

    private Roles role;

    @NotEmpty
    private String dateBirth;

    @NotEmpty
    private String password;
}
