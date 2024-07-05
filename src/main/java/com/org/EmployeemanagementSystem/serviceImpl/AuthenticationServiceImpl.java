package com.org.EmployeemanagementSystem.serviceImpl;
import com.org.EmployeemanagementSystem.entities.User;
import com.org.EmployeemanagementSystem.modals.request.RefreshTokenRequest;
import com.org.EmployeemanagementSystem.modals.request.SingInRequest;
import com.org.EmployeemanagementSystem.modals.request.SingUpRequest;
import com.org.EmployeemanagementSystem.modals.response.JWTAuthenticationResponse;
import com.org.EmployeemanagementSystem.repo.UserRepo;
import com.org.EmployeemanagementSystem.services.AuthenticationService;
import com.org.EmployeemanagementSystem.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    JWTService jwtService;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public User singUp(SingUpRequest singUpRequest){
        User user = new User();
        user.setEmailId(singUpRequest.getEmailId());
        user.setUsername(singUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(singUpRequest.getPassword()));
        user.setRole(singUpRequest.getRole());
        return userRepo.save(user);
    }
     public JWTAuthenticationResponse singIn(SingInRequest singInRequest){
         Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(singInRequest.getEmailId(),singInRequest.getPassword()));
         SecurityContextHolder.getContext().setAuthentication(authentication);

         var user = userRepo.findByEmailId(singInRequest.getEmailId()).orElseThrow(()->new UsernameNotFoundException("User not found"));
        var jwt = jwtService.generateToken(  user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
        JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        return jwtAuthenticationResponse;
    }

    public JWTAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String emailId=jwtService.extractUserName(refreshTokenRequest.getToken());
        UserDetails user= userRepo.findByEmailId(emailId).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
            var jwt=jwtService.generateToken(user);
            JWTAuthenticationResponse jwtAuthenticationResponse = new JWTAuthenticationResponse();
            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }
}
