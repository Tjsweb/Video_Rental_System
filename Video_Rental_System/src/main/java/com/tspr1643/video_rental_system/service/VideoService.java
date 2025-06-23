package com.tspr1643.video_rental_system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.tspr1643.video_rental_system.model.Video;
import com.tspr1643.video_rental_system.repository.VideoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public List<Video> getAvailableVideos() {
        return videoRepository.findByAvailableTrue();
    }

    public Video save(Video video) {
        return videoRepository.save(video);
    }

    public Optional<Video> findById(Long id) {
        return videoRepository.findById(id);
    }

    public void deleteById(Long id) {
        videoRepository.deleteById(id);
    }
}
