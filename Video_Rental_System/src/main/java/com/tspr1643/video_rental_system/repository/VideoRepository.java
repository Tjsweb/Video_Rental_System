package com.tspr1643.video_rental_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tspr1643.video_rental_system.model.Video;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByAvailableTrue();
}
