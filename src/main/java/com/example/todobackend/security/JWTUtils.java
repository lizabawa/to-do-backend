package com.example.todobackend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JWTUtils {
    @Value("${jwt-secret}")
    private String jwtSecret;

    @Value("${jwt-expiration-ms}")
    private int jwtExpirationMS;

    public String generateJwtToken(MyUserDetails myUserDetails){
        return Jwts.builder()
                .setSubject((myUserDetails.getUsername())) //get just the email address
                .setIssuedAt(new Date()) //set time to whatever the current time is
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMS)) //sets expiration date to current time + the jwt expiration date
                .signWith(SignatureAlgorithm.HS256, jwtSecret) //HS256 is the algorithm
                .compact(); //compacts everything together
    }
}
