package com.example.backend;

import com.example.backend.entity.User;
import com.example.backend.entity.UserLogin;
import com.example.backend.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class JwtTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testJwt(){
        String username = "username"; //unique
        User user = new User();
        user.setUsername(username);
        user.setId(1L);
        UserLogin userLogin = new UserLogin(user);
        String tokenByUser = JwtTokenUtil.generateToken(userLogin);
        System.out.println(tokenByUser);
        boolean token = JwtTokenUtil.validateToken(tokenByUser, userLogin);
        System.out.println("token = " + token);
        Claims claims = JwtTokenUtil.getClaimsFromToken(tokenByUser);
        System.out.println(claims);
    }

    @Test
    public void testPasswordEncoder(){
        String username = "username";
        String encode = passwordEncoder.encode(username);
        //$2a$10$x2pfZUpT3M9FWiK4lqKxZOlmuPL/yegiC0kDMSVA63irsQHcIFeKe
        System.out.println(encode);
    }
}
