package com.example.backend.utils;

import cn.hutool.core.date.DateUtil;
import com.example.backend.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.DateUtils;
import org.thymeleaf.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data //lombok will construct non-static or non-variable getter and setter
@Configuration //@Service, @Configuration
@ConfigurationProperties(prefix = "jwt") //@Value
//ConfigurationProperties:
//special modifier static and final will be different,
//getter modifier is static
//setter modifier is null
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static String secret;
    private static long expiration;
    private static String tokenHead;
    private static String tokenHeader;

    public static String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static Claims getClaimsFromToken(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            LOGGER.info("JWT validate failed: {}", token);
        }
        return claims;
    }


    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    public static String getUsernameFromToken(String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e){
            username = null;
        }
        return username;
    }

    public static boolean validateToken(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token) ;
    }

    public static boolean isTokenExpired(String token){
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    private static Date getExpiredDateFromToken(String token){
        Claims claimsFromToken = getClaimsFromToken(token);
        return claimsFromToken.getExpiration();
    }

    public static String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public String refreshHeadToken(String oldToken){
        if(StringUtils.isEmpty(oldToken)){
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if(StringUtils.isEmpty(token)){
            return null;
        }
        Claims claims = getClaimsFromToken(token);
        if(claims == null){
            return null;
        }
        if(isTokenExpired(token)){
            return null;
        }
        if(tokenRefreshJustBefore(token, 30*60)){
            return token;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }
    }

    private static boolean tokenRefreshJustBefore(String token, int time){
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        if(refreshDate.after(created) && refreshDate.before(DateUtil.offsetSecond(created, time))){
            return true;
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
