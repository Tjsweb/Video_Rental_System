package com.tspr1643.video_rental_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.tspr1643.video_rental_system.service.UserService;
import com.tspr1643.video_rental_system.model.User;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(Principal principal) {
        return ResponseEntity.ok("Logged in as: " + principal.getName());
    }
}
