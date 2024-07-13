package com.org.EmployeemanagementSystem.config;

import com.org.EmployeemanagementSystem.entities.User;
import com.org.EmployeemanagementSystem.services.JWTService;
import com.org.EmployeemanagementSystem.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JWTAuthenticatioFilter extends OncePerRequestFilter {
    public final JWTService jwtService;
    public final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      final String authHeader=request.getHeader("Authorization");
      final String jwt;
      final String username;
        if (request.getRequestURI().equals("/auth/signin") || request.getRequestURI().equals("/user")) {
            filterChain.doFilter(request, response);
            return;
        }
      if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader,"Bearer ")){
          filterChain.doFilter(request,response);
          return;
      }
      jwt=authHeader.substring(7);
        username=jwtService.extractUserName(jwt);
      if(org.apache.commons.lang3.StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext()==null){
          String role = jwtService.extractRole(jwt);
          GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
          UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);

          if(jwtService.isTokenValid(jwt, (User) userDetails)){
              SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
              UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
              token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              securityContext.setAuthentication(token);
              SecurityContextHolder.setContext(securityContext);
          }
      }
      filterChain.doFilter(request,response);
    }
}
