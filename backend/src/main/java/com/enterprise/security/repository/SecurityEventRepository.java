package com.enterprise.security.repository;

import com.enterprise.security.entity.SecurityEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityEventRepository
        extends JpaRepository<SecurityEvent, Long> {

    Page<SecurityEvent> findBySeverity(
            String severity,
            Pageable pageable
    );

    Page<SecurityEvent> findByEventType(
            String eventType,
            Pageable pageable
    );

    Page<SecurityEvent> findBySeverityAndEventType(
            String severity,
            String eventType,
            Pageable pageable
    );
}