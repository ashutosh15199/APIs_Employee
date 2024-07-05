package com.org.EmployeemanagementSystem.services;

import com.org.EmployeemanagementSystem.entities.User;
import com.org.EmployeemanagementSystem.modals.request.RefreshTokenRequest;
import com.org.EmployeemanagementSystem.modals.request.SingInRequest;
import com.org.EmployeemanagementSystem.modals.request.SingUpRequest;
import com.org.EmployeemanagementSystem.modals.response.JWTAuthenticationResponse;

public interface AuthenticationService {
    public User singUp(SingUpRequest singUpRequest);
    public JWTAuthenticationResponse singIn(SingInRequest singInRequest);
    public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
