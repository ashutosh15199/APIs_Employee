package com.org.EmployeemanagementSystem.repo;

import com.org.EmployeemanagementSystem.entities.Role;
import com.org.EmployeemanagementSystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {


    Optional<User> findByEmailId(String emailId);


}