package com.example.Hypro_wash.repository;

import com.example.Hypro_wash.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User>  findByUsername(String username);
}