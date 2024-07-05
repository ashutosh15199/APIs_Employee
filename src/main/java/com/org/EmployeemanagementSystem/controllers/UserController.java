package com.org.EmployeemanagementSystem.controllers;

import com.org.EmployeemanagementSystem.entities.User;
import com.org.EmployeemanagementSystem.modals.request.UserRequestModel;
import com.org.EmployeemanagementSystem.modals.response.BaseResponse;
import com.org.EmployeemanagementSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user",consumes = "application/json",produces = "application/json")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/create-user")
    public ResponseEntity<BaseResponse> createUserByAdmin(@RequestBody UserRequestModel userRequestModel){
       BaseResponse response = userService.createUserbyAdmin(userRequestModel);
        return new ResponseEntity<>(response, HttpStatus.resolve(response.getStatus()));
    }

}
