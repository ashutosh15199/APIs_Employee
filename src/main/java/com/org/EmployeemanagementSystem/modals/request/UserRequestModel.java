package com.org.EmployeemanagementSystem.modals.request;

import com.org.EmployeemanagementSystem.entities.Role;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserRequestModel {
    private String username;
    private int employeeAge;
    private String companyName;
    private String jobTitle;
    private int totalSalary;
    private String mobileNumber;
    private String emailId;
    private String password;
    private Role role;
    private LocalDateTime createdAt;

}

