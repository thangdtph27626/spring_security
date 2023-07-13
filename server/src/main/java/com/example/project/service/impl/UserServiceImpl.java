package com.example.project.service.impl;

import com.example.project.model.UserDetailCustom;
import com.example.project.entity.Users;
import com.example.project.repository.UserRepository;
import com.example.project.request.ProcessOAuthPostLoginRequest;
import com.example.project.request.UserRequest;
import com.example.project.response.UserResponse;
import com.example.project.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author thangdt
 */
@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public PageImpl<UserResponse> getAll(int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Users> users = userRepository.findAll(pageable);
        List<UserResponse> customUsers = new ArrayList<>();
        users.forEach(user -> {
            customUsers.add(new UserResponse(user));
        });
        return new PageImpl<>(customUsers, pageable, users.getTotalElements());
    }

    @Override
    public Users create(@Valid final UserRequest request) {
        Users user = new Users(request);
        return userRepository.save(user);
    }

    @Override
    public Users update(String id, @Valid final UserRequest request) {
        Optional<Users> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("user not exit");
        }
        Users user = new Users(request);
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public Users findById(String id) {
        Optional<Users> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RuntimeException("user not exit");
        }
        return user.get();
    }

    @Override
    public UserDetailCustom loadCustomsUserById(String id) {
        Optional<Users> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RuntimeException("user not exit");
        }
        return new UserDetailCustom(user.get());
    }

    @Override
    public boolean delete(String id) {
        Optional<Users> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new RuntimeException("user not exit");
        }
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public Users signUp(UserRequest request) {
        return null;
    }

    @Override
    public Users processOAuthPostLogin(ProcessOAuthPostLoginRequest request) {
        Optional<Users> optional = userRepository.findByEmail(request.getGmail());
        if (optional.isPresent()) {
            return optional.get();
        }
        Users user = new Users(request);
        return userRepository.save(user);
    }


}
