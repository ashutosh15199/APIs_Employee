package com.org.EmployeemanagementSystem.controllers;

import com.org.EmployeemanagementSystem.entities.User;
import com.org.EmployeemanagementSystem.modals.request.RefreshTokenRequest;
import com.org.EmployeemanagementSystem.modals.request.SingInRequest;
import com.org.EmployeemanagementSystem.modals.request.SingUpRequest;
import com.org.EmployeemanagementSystem.modals.response.JWTAuthenticationResponse;
import com.org.EmployeemanagementSystem.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping("/singup")

    public ResponseEntity<User> singUp(@RequestBody SingUpRequest singUpRequest){
        return ResponseEntity.ok(authenticationService.singUp(singUpRequest));
    }

    @PostMapping("/singin")
    public ResponseEntity<JWTAuthenticationResponse> singIn(@RequestBody SingInRequest singInRequest){
        return ResponseEntity.ok(authenticationService.singIn(singInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
