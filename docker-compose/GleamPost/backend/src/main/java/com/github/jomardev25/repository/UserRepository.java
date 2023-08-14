package com.github.jomardev25.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jomardev25.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    
}