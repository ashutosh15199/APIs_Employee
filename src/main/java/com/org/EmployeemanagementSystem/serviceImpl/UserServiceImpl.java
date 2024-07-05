package com.org.EmployeemanagementSystem.serviceImpl;

import com.org.EmployeemanagementSystem.entities.User;
import com.org.EmployeemanagementSystem.modals.request.UserRequestModel;
import com.org.EmployeemanagementSystem.modals.response.BaseResponse;
import com.org.EmployeemanagementSystem.repo.UserRepo;
import com.org.EmployeemanagementSystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public BaseResponse createUserbyAdmin(UserRequestModel userRequestModel) {
        BaseResponse<User> response = new BaseResponse<>();

        try {

            if (userRequestModel == null || userRequestModel.getUsername() == null || userRequestModel.getPassword() == null) {
                throw new RuntimeException("Invalid user data");
            }

            User user = new User();
            user.setUsername(userRequestModel.getUsername());
            user.setPassword(userRequestModel.getPassword());
            user.setRole(userRequestModel.getRole());
            user = userRepo.save(user);
            response.setData(user);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("User created successfully");

        } catch (RuntimeException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("An error occurred while creating the user");
            response.setInfo(e.getMessage());
        }

        return response;
    }


    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepo.findByEmailId(username).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            }
        };

    }


}