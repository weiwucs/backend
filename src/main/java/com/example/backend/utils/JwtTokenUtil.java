package com.example.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import com.example.backend.entity.User;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data //lombok will construct non-static or non-variable getter and setter
@Configuration //@Service, @Configuration
@ConfigurationProperties(prefix = "jwt") //@Value
//ConfigurationProperties:
//special modifier static and final will be different,
//getter modifier is static
//setter modifier is null
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static String secret;
    private static long expiration;
    private static String tokenHead;
    private static String tokenHeader;

    public static String createTokenByUser(User user){
        return JWT.create()
                .withIssuedAt(new Date())
                .withClaim("userId", user.getId())
                .withClaim("username", user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(Algorithm.HMAC512(secret));
    }

    public static DecodedJWT decodeToken(String token){
        try {
            Verification verification = JWT.require(Algorithm.HMAC512(secret));
            JWTVerifier jwtVerifier = verification.build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return decodedJWT;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (JWTVerificationException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validateToken(String token){
        try {
            Verification verification = JWT.require(Algorithm.HMAC512(secret));
            JWTVerifier jwtVerifier = verification.build();

            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            if(decodedJWT != null){
                return true;
            }
            return false;
        } catch (TokenExpiredException e){
            e.printStackTrace();
        }
        return false;
    }

    public static String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        JwtTokenUtil.secret = secret;
    }

    public static long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        JwtTokenUtil.expiration = expiration;
    }

    public static String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        JwtTokenUtil.tokenHead = tokenHead;
    }

    public static String getTokenHeader() {
        return tokenHeader;
    }

    public void setTokenHeader(String tokenHeader) {
        JwtTokenUtil.tokenHeader = tokenHeader;
    }
}
