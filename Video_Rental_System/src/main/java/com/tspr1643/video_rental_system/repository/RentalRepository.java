package com.tspr1643.video_rental_system.repository;

import com.tspr1643.video_rental_system.model.Rental;
import com.tspr1643.video_rental_system.model.User;
import com.tspr1643.video_rental_system.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserAndReturnedFalse(User user);
    Optional<Rental> findByUserAndVideoAndReturnedFalse(User user, Video video);
}