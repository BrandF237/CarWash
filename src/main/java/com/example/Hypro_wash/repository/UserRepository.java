package com.example.Hypro_wash.repository;

import com.example.Hypro_wash.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}