package com.example.project.service;

import com.example.project.model.UserDetailCustom;
import com.example.project.entity.Users;
import com.example.project.request.ProcessOAuthPostLoginRequest;
import com.example.project.request.UserRequest;
import com.example.project.response.UserResponse;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;

/**
 * @author thangdt
 */
public interface UserService {

    Page<UserResponse> getAll(int page);

    Users create(@Valid final UserRequest request);

    Users update(String id, @Valid final UserRequest request);

    Users findById(String id);

    UserDetailCustom loadCustomsUserById(String id);

    boolean delete(String id);

    Users signUp(@Valid final UserRequest request);

    Users processOAuthPostLogin(ProcessOAuthPostLoginRequest request);

}
