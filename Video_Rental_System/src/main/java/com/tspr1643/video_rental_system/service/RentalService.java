package com.tspr1643.video_rental_system.service;

import com.tspr1643.video_rental_system.model.*;
import com.tspr1643.video_rental_system.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

  private final RentalRepository rentalRepository;
  private final UserRepository userRepository;
  private final VideoRepository videoRepository;

  public Rental rentVideo(Long videoId) {
    User user = getCurrentUser();
    if (rentalRepository.findByUserAndReturnedFalse(user).size() >= 2) {
      throw new RuntimeException("You can only rent up to 2 videos at a time.");
    }

    Video video = videoRepository.findById(videoId)
        .orElseThrow(() -> new RuntimeException("Video not found."));

    Rental rental = Rental.builder()
        .user(user)
        .video(video)
        .rentedAt(LocalDateTime.now())
        .returned(false)
        .build();
    return rentalRepository.save(rental);
  }

  public Rental returnVideo(Long videoId) {
    User user = getCurrentUser();
    Video video = videoRepository.findById(videoId)
        .orElseThrow(() -> new RuntimeException("Video not found."));

    Rental rental = rentalRepository.findByUserAndVideoAndReturnedFalse(user, video)
        .orElseThrow(() -> new RuntimeException("You did not rent this video or already returned."));

    rental.setReturned(true);
    return rentalRepository.save(rental);
  }

  private User getCurrentUser() {
    String username = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        .getUsername();
    return userRepository.findByEmail(username)
        .orElseThrow(() -> new RuntimeException("User not found."));
  }
}