package com.enterprise.security.controller;

import com.enterprise.security.dto.SecurityEventRequest;
import com.enterprise.security.entity.SecurityEvent;
import com.enterprise.security.service.SecurityEventService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class SecurityEventController {

    private final SecurityEventService securityEventService;

    public SecurityEventController(
            SecurityEventService securityEventService) {

        this.securityEventService = securityEventService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SECURITY_ANALYST')")
    public ResponseEntity<SecurityEvent> createEvent(
            @Valid @RequestBody SecurityEventRequest request) {

        SecurityEvent event =
                securityEventService.createEvent(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(event);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SECURITY_ANALYST')")
    public ResponseEntity<Page<SecurityEvent>> getEvents(

            @RequestParam(required = false)
            String severity,

            @RequestParam(required = false)
            String eventType,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        Page<SecurityEvent> events =
                securityEventService.getEvents(
                        severity,
                        eventType,
                        PageRequest.of(page, size)
                );

        return ResponseEntity.ok(events);
    }
}