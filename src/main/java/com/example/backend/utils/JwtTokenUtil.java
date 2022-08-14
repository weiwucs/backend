package com.example.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static String secret;
    private static long expiration;
    private static String tokenHead;
    private static String tokenHeader;

    public static String createToken(String username){
        System.out.println(secret);
        System.out.println(tokenHead);
        return JWT.create()
                .withIssuedAt(new Date())
                .withClaim("username", username)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(Algorithm.HMAC512(secret));
    }

    public static String validateToken(String token){
        try {
            Verification verification = JWT.require(Algorithm.HMAC512(secret));
            JWTVerifier jwtVerifier = verification.build();

            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            if(decodedJWT != null){
                return decodedJWT.getClaim("username").asString();
            }
            return "";
        } catch (TokenExpiredException e){
            e.printStackTrace();
        }
        return "";
    }

}
