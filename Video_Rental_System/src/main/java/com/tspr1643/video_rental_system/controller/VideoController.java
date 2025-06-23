package com.tspr1643.video_rental_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.tspr1643.video_rental_system.model.Video;
import com.tspr1643.video_rental_system.service.VideoService;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/available")
    public ResponseEntity<List<Video>> getAvailableVideos() {
        return ResponseEntity.ok(videoService.getAvailableVideos());
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Video> createVideo(@RequestBody Video video) {
        return ResponseEntity.ok(videoService.save(video));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id, @RequestBody Video updatedVideo) {
        return videoService.findById(id)
                .map(video -> {
                    video.setTitle(updatedVideo.getTitle());
                    video.setDirector(updatedVideo.getDirector());
                    video.setGenre(updatedVideo.getGenre());
                    video.setAvailable(updatedVideo.isAvailable());
                    return ResponseEntity.ok(videoService.save(video));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
        videoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
