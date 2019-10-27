package com.apirestjwt.main.security;


import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.apirestjwt.main.security.jwt.ConfigTokenJWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenAuthenticationService {
    
    public static void addAuthentication(HttpServletResponse res, String username) {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + ConfigTokenJWT.getExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, ConfigTokenJWT.getSecret())
                .compact();

        String token = ConfigTokenJWT.getPrefix() + " " + JWT;
        res.addHeader(ConfigTokenJWT.getHeader(), token);
 
        try {
            res.getOutputStream().print(token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Authentication getByToken(String token) {
    	String user = Jwts.parser()
                .setSigningKey(ConfigTokenJWT.getSecret())
                .parseClaimsJws(token.replace(ConfigTokenJWT.getPrefix(), ""))
                .getBody()
                .getSubject();
        return user != null ? new UsernamePasswordAuthenticationToken(user, null, null) : null;
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(ConfigTokenJWT.getHeader());
        if (token != null) {
            return getByToken(token);
        }
        return null;
    }
}
