package com.example.backend;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.backend.entity.User;
import com.example.backend.utils.JwtTokenUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

@SpringBootTest
public class JwtTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testJwt(){
        String username = "username";
        User user = new User();
        user.setUsername(username);
        user.setId(1L);
        String tokenByUser = JwtTokenUtil.createTokenByUser(user);
        System.out.println(tokenByUser);
        boolean token = JwtTokenUtil.validateToken(tokenByUser);
        System.out.println("token = " + token);
        DecodedJWT decodedJWT = JwtTokenUtil.decodeToken(tokenByUser);
        String header = decodedJWT.getHeader();
        String payload = decodedJWT.getPayload();
        String signature = decodedJWT.getSignature();
        String subject = decodedJWT.getSubject();
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(header + " " + payload + " " + signature);
        System.out.println(subject + " " + claims);
    }

    @Test
    public void testPasswordEncoder(){
        String username = "username";
        String encode = passwordEncoder.encode(username);
        //$2a$10$x2pfZUpT3M9FWiK4lqKxZOlmuPL/yegiC0kDMSVA63irsQHcIFeKe
        System.out.println(encode);
    }
}
