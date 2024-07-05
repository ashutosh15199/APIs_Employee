//package com.org.EmployeemanagementSystem.services;
//import com.org.EmployeemanagementSystem.entities.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.Collections;
//@Service
//public class UserDetailService implements UserDetailsService {
//    @Autowired
//    UserService userService;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.findByUsername(username);
//        if(user==null){
//            throw new UsernameNotFoundException("User Not Found with Username"+username);
//
//        }
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.isEnable(),
//                true,
//                true,
//                true,
//                getAuthorities(user)
//        );
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
//        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()));
//    }
//}
