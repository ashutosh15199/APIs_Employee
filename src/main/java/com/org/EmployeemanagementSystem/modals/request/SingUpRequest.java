package com.org.EmployeemanagementSystem.modals.request;

import com.org.EmployeemanagementSystem.entities.Role;
import lombok.Data;

@Data
public class SingUpRequest {
    private String username;
    private String emailId;
    private String password;
    private Role role;
}
