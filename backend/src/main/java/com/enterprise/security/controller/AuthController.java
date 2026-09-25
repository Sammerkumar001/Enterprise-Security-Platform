package com.enterprise.security.controller;

import com.enterprise.security.dto.LoginRequest;
import com.enterprise.security.dto.LoginResponse;
import com.enterprise.security.dto.RegisterRequest;
import com.enterprise.security.entity.User;
import com.enterprise.security.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @Valid @RequestBody RegisterRequest request) {

        User user = authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "User registered successfully",
                        "email", user.getEmail(),
                        "role", user.getRole().name()
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        LoginResponse response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    // Temporary development endpoint.
    // Used only to create a SECURITY_ANALYST account
    // during development.
    @PostMapping("/promote-analyst")
    public ResponseEntity<?> promoteToSecurityAnalyst(
            @RequestParam String email) {

        User user =
                authService.promoteToSecurityAnalyst(email);

        return ResponseEntity.ok(
                Map.of(
                        "message", "User promoted successfully",
                        "email", user.getEmail(),
                        "role", user.getRole().name()
                )
        );
    }
}