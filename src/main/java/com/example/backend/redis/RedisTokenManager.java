package com.example.backend.redis;

public class RedisTokenManager implements TokenManager{

    @Override
    public String createToken(String username) {
        return null;
    }

    @Override
    public boolean checkToken(String token) {
        return false;
    }

    @Override
    public String getToken(String username) {
        return null;
    }

    @Override
    public void deleteToken(String username) {

    }
}
