package com.org.EmployeemanagementSystem.modals.response;

import lombok.Data;

@Data
public class JWTAuthenticationResponse {

    private String token;
    private String refreshToken;
}
