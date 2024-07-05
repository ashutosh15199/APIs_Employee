package com.org.EmployeemanagementSystem.modals.request;

import lombok.Data;

@Data
public class SingInRequest {
    private String emailId;
    private String password;
}
