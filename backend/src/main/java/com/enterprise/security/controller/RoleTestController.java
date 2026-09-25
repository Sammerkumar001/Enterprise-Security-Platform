package com.enterprise.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleTestController {

    @GetMapping("/api/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        return "Welcome ADMIN! You have administrator access.";
    }

    @GetMapping("/api/security")
    @PreAuthorize("hasRole('SECURITY_ANALYST')")
    public String securityEndpoint() {
        return "Welcome SECURITY ANALYST! You have security analyst access.";
    }

    @GetMapping("/api/employee")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public String employeeEndpoint() {
        return "Welcome EMPLOYEE! You have employee access.";
    }
}