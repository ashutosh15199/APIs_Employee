package com.org.EmployeemanagementSystem.serviceImpl;
import com.org.EmployeemanagementSystem.entities.User;
import com.org.EmployeemanagementSystem.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JWTServiceImpl implements JWTService {
    public String generateToken(User user){
        if(user==null || user.getUsername()== null || user.isEnabled()==false || user.getUsername().isEmpty()){
            throw new RuntimeException("Invalid credentials");
        }
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("userId",user.getEmployeeId());
        claims.put("role",user.getRole().toString());
        try {
            return Jwts.builder().setClaims(claims)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                    .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                    .compact();
        }catch (Exception e){
            throw new RuntimeException("Error Generating token");
        }
    }

    public String generateRefreshToken(Map<String,Object> extraClaims, UserDetails user){
        return Jwts.builder().setClaims(extraClaims).setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+604800000))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUserName(String token){
        return Jwts.parser().setSigningKey(getSiginKey()).parseClaimsJws(token).getBody().getSubject();
    }
    public String extractRole(String token) {
        return Jwts.parser().setSigningKey(getSiginKey()).parseClaimsJws(token).getBody().get("role", String.class);
    }
    private <T>T extractClaims(String token, Function<Claims,T>claimsResolvers){
      final Claims claims = extractAllClaims(token);
      return claimsResolvers.apply(claims);
    }
    private Key getSiginKey(){
        byte[] key= Decoders.BASE64.decode("AJHEU4983893U7Y4HDBDFHURE838938UY3YRGFDHUE48U38U73UYE3HGDBHFUEU3EYYU3H");
        return Keys.hmacShaKeyFor(key);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSiginKey()).build().parseClaimsJws(token).getBody();
    }
    public boolean isTokenValid(String token,User user){
        final String username= extractUserName(token);
        return (username.equals(user.getUsername()) && !isTokenExpire(token));
    }
    private boolean isTokenExpire(String token){
        return extractClaims(token,Claims::getExpiration).before(new Date());
    }
}
