package com.org.EmployeemanagementSystem.services;

import com.org.EmployeemanagementSystem.entities.User;
import com.org.EmployeemanagementSystem.modals.request.UserRequestModel;
import com.org.EmployeemanagementSystem.modals.response.BaseResponse;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService {
    public BaseResponse createUserbyAdmin(UserRequestModel userRequestModel);
    public UserDetailsService userDetailsService();
}

