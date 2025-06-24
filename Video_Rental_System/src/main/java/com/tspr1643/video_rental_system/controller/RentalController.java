package com.tspr1643.video_rental_system.controller;

import com.tspr1643.video_rental_system.model.Rental;
import com.tspr1643.video_rental_system.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class RentalController {

  private final RentalService rentalService;

  @PostMapping("/{videoId}/rent")
  @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
  public ResponseEntity<Rental> rentVideo(@PathVariable Long videoId) {
    return ResponseEntity.ok(rentalService.rentVideo(videoId));
  }

  @PostMapping("/{videoId}/return")
  @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
  public ResponseEntity<Rental> returnVideo(@PathVariable Long videoId) {
    return ResponseEntity.ok(rentalService.returnVideo(videoId));
  }
}