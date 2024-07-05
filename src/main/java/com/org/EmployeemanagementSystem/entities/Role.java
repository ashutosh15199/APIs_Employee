package com.org.EmployeemanagementSystem.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



@RequiredArgsConstructor
public enum Role {
   ADMIN,
    USER


}
