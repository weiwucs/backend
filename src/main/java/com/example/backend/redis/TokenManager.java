package com.example.backend.redis;

public interface TokenManager {
    public String createToken(String username);
    public boolean checkToken(String token);
    public String getToken(String username);
    public void deleteToken(String username);
}
