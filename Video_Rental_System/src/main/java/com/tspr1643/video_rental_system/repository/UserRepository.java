package com.tspr1643.video_rental_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tspr1643.video_rental_system.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
