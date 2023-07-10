package com.example.project.repository;

import com.example.project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author thangdt
 */
public interface UserRepository extends JpaRepository<Users, String> {

   Optional<Users> findByEmail(String email);

}
