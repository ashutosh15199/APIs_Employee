package com.org.EmployeemanagementSystem.services;

import com.org.EmployeemanagementSystem.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.Optional;

public interface JWTService {
    public String extractUserName(String token);
    public String generateToken(UserDetails userDetails);
    public boolean isTokenValid(String token, UserDetails userDetails);
    public String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails);


}
