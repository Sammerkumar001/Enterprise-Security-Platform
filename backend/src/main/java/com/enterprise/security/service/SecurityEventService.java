package com.enterprise.security.service;

import com.enterprise.security.dto.SecurityEventRequest;
import com.enterprise.security.entity.SecurityEvent;
import com.enterprise.security.repository.SecurityEventRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SecurityEventService {

    private final SecurityEventRepository securityEventRepository;

    public SecurityEventService(
            SecurityEventRepository securityEventRepository) {

        this.securityEventRepository = securityEventRepository;
    }

    public SecurityEvent createEvent(SecurityEventRequest request) {

        SecurityEvent event = new SecurityEvent();

        event.setTimestamp(LocalDateTime.now());
        event.setSourceIp(request.getSourceIp());
        event.setDestinationIp(request.getDestinationIp());
        event.setUsername(request.getUsername());
        event.setEventType(request.getEventType());
        event.setSeverity(request.getSeverity());
        event.setMessage(request.getMessage());
        event.setSourceSystem(request.getSourceSystem());

        return securityEventRepository.save(event);
    }

    public List<SecurityEvent> getAllEvents() {
        return securityEventRepository.findAll();
    }
}