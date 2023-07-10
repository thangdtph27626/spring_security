package com.example.project.entity;

import com.example.project.request.ProcessOAuthPostLoginRequest;
import com.example.project.request.UserRequest;
import com.example.project.utill.ConvertDate;
import com.example.project.utill.Roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author thangdt
 */
@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private Roles role;

    @Column(name = "date_birth")
    private Long dateBirth;

    public Users(UserRequest request) {
        this.fullName = request.getFullName();
        this.phoneNumber = request.getPhoneNumber();
        this.email = request.getEmail();
        this.gender = request.isGender();
        this.role = request.getRole();
        this.password = new BCryptPasswordEncoder().encode(request.getPassword());
        this.dateBirth = ConvertDate.convertStringToLong(request.getDateBirth());
    }

    public Users(ProcessOAuthPostLoginRequest request) {
        this.fullName = request.getFullName();
        this.email = request.getGmail();
        this.role = Roles.USER;
    }

}
