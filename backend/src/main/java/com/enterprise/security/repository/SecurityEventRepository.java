package com.enterprise.security.repository;

import com.enterprise.security.entity.SecurityEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecurityEventRepository
        extends JpaRepository<SecurityEvent, Long> {
}